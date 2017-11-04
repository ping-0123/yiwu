package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.LessonTemplateVO;
import com.yinzhiwu.yiwu.model.view.LessonTemplateVO.LessonTemplateVOConverter;
import com.yinzhiwu.yiwu.service.LessonTemplateService;
import com.yinzhiwu.yiwu.service.QiniuService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月2日 下午9:43:17
*
*/

@RequestMapping("/system/lessonTemplates")
@Controller
public class LessonTemplateController extends BaseController
{
	@Autowired private LessonTemplateService lessonTemplateService;
	@Autowired private QiniuService qiniuService;
	
	@GetMapping(value="/list")
	public String list(Integer courseTemplateId, Model model){
		model.addAttribute("courseTemplateId", courseTemplateId);
		return "lessonTemplates/list";
	}
	
	@GetMapping(value="/{id}/updateForm")
	public String showUpdateForm(@PathVariable(name="id") Integer id, Model model)
			throws DataNotFoundException{
		LessonTemplate lesson = lessonTemplateService.get(id);
		LessonTemplateVO template =  LessonTemplateVOConverter.INSTANCE.fromPO(lesson);
		model.addAttribute("template", template);
		return "lessonTemplate/updateForm";
	}
	

	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findLessonTemplateDatatable(Integer courseTempId, HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
		ServletRequestUtils.transferQueryParamter(parameter, LessonTemplateVO.class);
		
		DataTableBean<LessonTemplate>  table = lessonTemplateService.findDataTableByCourseTemplateId(parameter,courseTempId);
		List<LessonTemplateVO> vos = new ArrayList<>();
		for(LessonTemplate lesson: table.getData()){
			vos.add(LessonTemplateVOConverter.INSTANCE.fromPO(lesson));
		}
		
		return new DataTableBean<>(table.getDraw(), table.getRecordsTotal(), table.getRecordsFiltered(), vos, table.getError());
	}
	
	@PutMapping(value="/{id}")
	@ResponseBody
	public YiwuJson<?> doUpdate(@PathVariable(name="id") Integer id, LessonTemplate template) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException
	{
		lessonTemplateService.modify(id, template);
		return YiwuJson.createBySuccess(
				LessonTemplateVOConverter.INSTANCE.fromPO(lessonTemplateService.get(id)));
	}
	
	@PutMapping(value="/{id}/connotation/pictureUri")
	@ResponseBody
	public YiwuJson<?> updatePictureUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPictureUri(fileKey);
		lessonTemplateService.update(template);
		
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/pictureUri")
	@ResponseBody
	public YiwuJson<?> deletePictureUri(@PathVariable(name="id") Integer id) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPictureUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping(value="/{id}/connotation/audioUri")
	@ResponseBody
	public YiwuJson updateAudioUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setAudioUri(fileKey);
		template.ensureConnotation().setAudioName(fileKey);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/audioUri")
	@ResponseBody
	public YiwuJson<?> deleteAudioUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setAudioUri(null);
		template.ensureConnotation().setAudioName(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	// standard video
	@PutMapping(value="/{id}/connotation/standardVideoPosterUri")
	@ResponseBody
	public YiwuJson<?> updateStandardVideoPosterUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setStandardVideoPosterUri(fileKey);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/standardVideoPosterUri")
	@ResponseBody
	public YiwuJson<?> deleteStandardVideoPosterUri(@PathVariable(name="id") Integer id) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setStandardVideoPosterUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping(value="/{id}/connotation/standardVideoUri")
	@ResponseBody
	public YiwuJson<?> updateStandardVideoUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setStandardVideoUri(fileKey);;
		template.ensureConnotation().setStandardVideoTitle(fileName);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/standardVideoUri")
	@ResponseBody
	public YiwuJson<?> deleteStandardVideoUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setStandardVideoUri(null);
		template.ensureConnotation().setStandardVideoTitle(null);
		template.ensureConnotation().setStandardVideoPosterUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	// puzzle video
	@PutMapping(value="/{id}/connotation/puzzleVideoPosterUri")
	@ResponseBody
	public YiwuJson<?> updatePuzzleVideoPosterUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPuzzleVideoPosterUri(fileKey);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/puzzleVideoPosterUri")
	@ResponseBody
	public YiwuJson<?> deletePuzzleVideoPosterUri(@PathVariable(name="id") Integer id) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPuzzleVideoPosterUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping(value="/{id}/connotation/puzzleVideoUri")
	@ResponseBody
	public YiwuJson<?> updatePuzzleVideoUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPuzzleVideoUri(fileKey);;
		template.ensureConnotation().setStandardVideoTitle(fileName);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/puzzleVideoUri")
	@ResponseBody
	public YiwuJson<?> deletePuzzleVideoUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPuzzleVideoUri(null);
		template.ensureConnotation().setPuzzleVideoTitle(null);
		template.ensureConnotation().setPuzzleVideoPosterUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	// practical video
	@PutMapping(value="/{id}/connotation/practicalVideoPosterUri")
	@ResponseBody
	public YiwuJson<?> updatePracticalVideoPosterUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPracticalVideoPosterUri(fileKey);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/practicalVideoPosterUri")
	@ResponseBody
	public YiwuJson<?> deletePracticalVideoPosterUri(@PathVariable(name="id") Integer id) throws DataNotFoundException
	{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPracticalVideoPosterUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
	
	@PutMapping(value="/{id}/connotation/practicalVideoUri")
	@ResponseBody
	public YiwuJson<?> updatePracticalVideoUri(@PathVariable(name="id") Integer id, String fileKey, String fileName) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPracticalVideoUri(fileKey);;
		template.ensureConnotation().setPracticalVideoTitle(fileName);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess(qiniuService.generateFileUrl(fileKey));
	}
	
	@DeleteMapping(value="/{id}/connotation/practicalVideoUri")
	@ResponseBody
	public YiwuJson<?> deletePracticalVideoUri(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		LessonTemplate template = lessonTemplateService.get(id);
		template.ensureConnotation().setPracticalVideoUri(null);
		template.ensureConnotation().setPracticalVideoTitle(null);
		template.ensureConnotation().setPracticalVideoPosterUri(null);
		lessonTemplateService.update(template);
		return YiwuJson.createBySuccess();
	}
}
