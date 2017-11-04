package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CourseTemplate;
import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.CourseTemplateVO;
import com.yinzhiwu.yiwu.model.view.CourseTemplateVO.CourseTemplateVOConverter;
import com.yinzhiwu.yiwu.model.view.LessonTemplateVO;
import com.yinzhiwu.yiwu.model.view.LessonTemplateVO.LessonTemplateVOConverter;
import com.yinzhiwu.yiwu.service.ConnotationProviderService;
import com.yinzhiwu.yiwu.service.CourseTemplateService;
import com.yinzhiwu.yiwu.service.DanceGradeYzwService;
import com.yinzhiwu.yiwu.service.DanceYzwService;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.LessonTemplateService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
*@Author ping
*@Time  创建时间:2017年10月25日下午1:56:41
*
*/

@Controller
@RequestMapping("/system/courseTemplates")
public class CourseTemplateController extends BaseController{
	
	@Autowired  private CourseTemplateService courseTemplateService;
	@Autowired private DepartmentYzwService deptmentService;
	@Autowired private ConnotationProviderService connotationProviderService;
	@Autowired private DanceGradeYzwService danceGradeService;
	@Autowired private DanceYzwService danceService;
	@Qualifier("qiniuServiceImpl")
	@Autowired private FileService qiniuService;
	@Autowired private LessonTemplateService lessonTemplateService;
	
	@GetMapping(value="/list")
	public String list(){
		return "courseTemplates/list";
	}
	
	@GetMapping
	public String home(){
		return "redirect:courseTemplates/list";
	}
	
	@GetMapping(value="/index")
	public String index(){
		return "redirect:list";
	}
	
	@GetMapping(value="/createForm")
	public String showCreateForm(Model model){
		//TODO 只输出可见的部门
		model.addAttribute("departments", deptmentService.findAll());
		model.addAttribute("providers", connotationProviderService.findAll());
		model.addAttribute("danceGrades", danceGradeService.findAll());
		model.addAttribute("dances", danceService.findAll());
		model.addAttribute("courseTypes", CourseType.getEffectiveCourseTypes());
		
		return "courseTemplates/createForm";
	}
	
	@GetMapping(value="/{id}/updateForm")
	public String showUpdateForm(@PathVariable(name="id") Integer id, Model model) throws DataNotFoundException{
		
		model.addAttribute("uploadToken", qiniuService.createAccessToken());
		model.addAttribute("template", 
				CourseTemplateVOConverter.INSTANCE.fromPO(courseTemplateService.get(id)));
//		model.addAttribute("departments", deptmentService.findAll());
//		model.addAttribute("providers", connotationProviderService.findAll());
//		model.addAttribute("danceGrades", danceGradeService.findAll());
//		model.addAttribute("dances", danceService.findAll());
//		model.addAttribute("courseTypes", CourseType.getEffectiveCourseTypes());
		return "courseTemplates/updateForm";
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
		ServletRequestUtils.transferQueryParamter(parameter, CourseTemplateVO.class);
		
		DataTableBean<CourseTemplate> table = courseTemplateService.findDataTable(parameter);
		List<CourseTemplateVO> vos = new ArrayList<CourseTemplateVO>();
		for(CourseTemplate course: table.getData()){
			vos.add(CourseTemplateVOConverter.INSTANCE.fromPO(course));
		}
		
		return new DataTableBean<>(table.getDraw(), table.getRecordsTotal(), table.getRecordsFiltered(), vos, table.getError());
		
	}
	
	
	@GetMapping("/subCourseTypes")
	@ResponseBody
	public YiwuJson<List<SubCourseType>>  getSubCourseTypes(CourseType courseType){
		
		return YiwuJson.createBySuccess(courseType.getSubCourseTypes());
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?>  doCreate(@Valid CourseTemplate course, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createBySuccess(getErrorsMessage(result));
		courseTemplateService.save(course);
		
		return YiwuJson.createBySuccess(CourseTemplateVOConverter.INSTANCE.fromPO(course));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> doUpdate(@PathVariable(name="id") Integer id,  CourseTemplate course, BindingResult result) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException{
		courseTemplateService.modify(id, course);
		
		return YiwuJson.createBySuccess(CourseTemplateVOConverter.INSTANCE.fromPO(course));
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public YiwuJson<?> doDelete(@PathVariable(name="id") Integer id){
		courseTemplateService.delete(id);
		
		return YiwuJson.createBySuccess();
	}
	
	@DeleteMapping("/{id}/connotation/pictureUri")
	@ResponseBody
	public YiwuJson<?> deletePictureUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		String pictureUri = template.getConnotation().getPictureUri();
		qiniuService.delete(pictureUri);
		
		template.getConnotation().setPictureUri(null);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping("/{id}/connotation/pictureUri")
	@ResponseBody
	public YiwuJson<?> updatePictureUri(@PathVariable(name="id") Integer id ,String fileKey) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		template.getConnotation().setPictureUri(fileKey);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@PutMapping("/{id}/connotation/audioUri")
	@ResponseBody
	public YiwuJson<?> updateAudioUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		template.getConnotation().setAudioUri(fileKey);
		template.getConnotation().setAudioName(fileName);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping("/{id}/connotation/audioUri")
	@ResponseBody
	public YiwuJson<?> deleteAudioUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		template.getConnotation().setAudioName(null);
		template.getConnotation().setAudioUri(null);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping("/{id}/connotation/videoPosterUri")
	@ResponseBody
	public YiwuJson<?> updateVideoPosterUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		template.getConnotation().setVideoPosterUri(fileKey);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@PutMapping("/{id}/connotation/videoUri")
	@ResponseBody
	public YiwuJson<?> updateVideoUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		template.getConnotation().setVideoUri(fileKey);
		template.getConnotation().setVideoTitle(fileName);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping("/{id}/connotation/videoUri")
	@ResponseBody
	public YiwuJson<?> deleteVideoUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		CourseTemplate template = courseTemplateService.get(id);
		template.getConnotation().setVideoPosterUri(null);
		template.getConnotation().setVideoTitle(null);
		template.getConnotation().setVideoUri(null);
		courseTemplateService.update(template);
		
		return YiwuJson.createBySuccess();
		
	}
	
	//lessonTemplate 模块
	@PostMapping(value="/{id}/lessonTemplates/datatable")
	@ResponseBody
	public DataTableBean<?> findLessonTemplateDatatable(@PathVariable(name="id") Integer courseTemplateId, HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
		ServletRequestUtils.transferQueryParamter(parameter, LessonTemplateVO.class);
		
		DataTableBean<LessonTemplate>  table = lessonTemplateService.findDataTableByCourseTemplateId(parameter,courseTemplateId);
		List<LessonTemplateVO> vos = new ArrayList<>();
		for(LessonTemplate lesson: table.getData()){
			vos.add(LessonTemplateVOConverter.INSTANCE.fromPO(lesson));
		}
		
		return new DataTableBean<>(table.getDraw(), table.getRecordsTotal(), table.getRecordsFiltered(), vos, table.getError());
	}
	
	@GetMapping(value="/{id}/lessonTemplates/list")
	public String list(@PathVariable(name="id")Integer courseTemplateId, Model model){
		model.addAttribute("courseTemplateId", courseTemplateId);
		return "courseTemplates/lessonTemplates/list";
	}
	
	@GetMapping(value="/{id}/lessonTemplates/{lessonTemplateId}/updateForm")
	public String showLessonTemplateUpdateForm(@PathVariable(name="lessonTemplateId") Integer lessonTemplateId , Model model)
			throws DataNotFoundException{
		LessonTemplate lesson = lessonTemplateService.get(lessonTemplateId);
		LessonTemplateVO template =  LessonTemplateVOConverter.INSTANCE.fromPO(lesson);
		model.addAttribute("template", template);
		//添加七牛云上传token
		model.addAttribute("uploadToken", qiniuService.createAccessToken());
		return "courseTemplates/lessonTemplates/updateForm";
	}
	
	@PutMapping(value="/{id}/lessonTemplates/{lessonTemplateId}")
	@ResponseBody
	public YiwuJson<?> doUpdate(@PathVariable(name="lessonTemplateId") Integer id, LessonTemplate template) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException
	{
		lessonTemplateService.modify(id, template);
		return YiwuJson.createBySuccess(
				LessonTemplateVOConverter.INSTANCE.fromPO(lessonTemplateService.get(id)));
	}
}
