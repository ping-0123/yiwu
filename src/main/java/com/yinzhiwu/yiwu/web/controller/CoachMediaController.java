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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaTag;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaType;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.CoachMediaVO;
import com.yinzhiwu.yiwu.model.view.CoachMediaVO.CoachMediaVOConverter;
import com.yinzhiwu.yiwu.service.CoachMediaService;
import com.yinzhiwu.yiwu.service.EmployeePostYzwService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;

/**
*@Author ping
*@Time  创建时间:2017年10月23日上午11:08:59
*
*/

@Controller
@RequestMapping(value="/system/coachMedia")
public class CoachMediaController extends BaseController{
	
	@Autowired private CoachMediaService coachMediaService;
	@Autowired private EmployeePostYzwService employeePostService;
	@Qualifier("qiniuServiceImpl")
	@Autowired private FileService qiniuService;
	@Autowired private EmployeeYzwService employeeService;
	
	@GetMapping
	public String index(){
		return "redirect:coachMedia/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		return "coachMedia/list";
	}
	
	@GetMapping(value="details")
	public String getMediaDetails(Integer coachId, Model model){
		model.addAttribute("coachId", coachId);
		model.addAttribute("uploadToken", qiniuService.createAccessToken());
		
		List<CoachMedia> medias = coachMediaService.findByCoachId(coachId);
		List<CoachMediaVO> headerMedias = new ArrayList<>();
		List<CoachMediaVO> certificateMedias = new ArrayList<>();
		List<CoachMediaVO> dailyMedias = new ArrayList<>();
		List<CoachMediaVO> danceMedias = new ArrayList<>();
		CoachMediaVOConverter converter =CoachMediaVOConverter.instance;
		for (CoachMedia media : medias) {
			switch (media.getTag()) {
			case HEADER:
				headerMedias.add(converter.fromPO(media));
				break;
			case CERTIFICATE:
				certificateMedias.add(converter.fromPO(media));
				break;
			case DAILY:
				dailyMedias.add(converter.fromPO(media));
				break;
			case DANCE:
				danceMedias.add(converter.fromPO(media));
			default:
				break;
			}
		}
		
		model.addAttribute("headerMedia", headerMedias.size()>0?headerMedias.get(0):null);
		model.addAttribute("certificateMedias", certificateMedias);
		model.addAttribute("dailyMedias", dailyMedias);
		model.addAttribute("danceMedias", danceMedias);
		
		return "coachMedia/details";
	}
	
	@GetMapping(value="createForm")
	public String showCreateForm(Integer coachId, MediaTag tag, Model model){
		model.addAttribute("uploadToken", qiniuService.createAccessToken());
		
		CoachMedia media = new CoachMedia();
		media.setCoach(employeeService.get(coachId));
		media.setTag(tag);
		
		switch (tag) {
		case DANCE:
			media.setType(MediaType.VIDEO);
			model.addAttribute("media", media);
			return "coachMedia/createForm_dance";
		default:
			media.setType(MediaType.IMAGE);
			model.addAttribute("media", media);
			return "coachMedia/createForm";
		}
		
		
	}
	
	@PostMapping
	@ResponseBody
	public YiwuJson<?> createNew(@Valid CoachMedia coachMedia, BindingResult result){
		if(result.hasErrors())
			return YiwuJson.createByErrorMessage(getErrorsMessage(result));
		
		coachMediaService.save(coachMedia);
		
		return YiwuJson.createBySuccess();
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> delete(@PathVariable(name="id") Integer id){
		coachMediaService.delete(id);
		return YiwuJson.createBySuccess();
	}
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<EmployeePostYzw> findDatatable(HttpServletRequest request){
		
		try {
			QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
			return employeePostService.findDataTableOfCoach(parameter);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InstantiationException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return null;
	}

}
