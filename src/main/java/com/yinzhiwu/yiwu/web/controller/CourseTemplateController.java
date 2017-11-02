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
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.SubCourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.CourseTemplateVO;
import com.yinzhiwu.yiwu.model.view.CourseTemplateVO.CourseTemplateVOConverter;
import com.yinzhiwu.yiwu.service.ConnotationProviderService;
import com.yinzhiwu.yiwu.service.CourseTemplateService;
import com.yinzhiwu.yiwu.service.DanceGradeYzwService;
import com.yinzhiwu.yiwu.service.DanceYzwService;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.FileService;
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
		model.addAttribute("template", courseTemplateService.get(id));
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
}
