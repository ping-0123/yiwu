package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@PutMapping(value="/{id}")
	@ResponseBody
	public YiwuJson<?> doUpdate(@PathVariable(name="id") Integer id, LessonTemplate template) throws IllegalArgumentException, IllegalAccessException, DataNotFoundException
	{
		lessonTemplateService.modify(id, template);
		return YiwuJson.createBySuccess(
				LessonTemplateVOConverter.INSTANCE.fromPO(lessonTemplateService.get(id)));
	}
	
	//lessonTemplate 模块
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
}
