package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaTag;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaType;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CoachMediaVO;
import com.yinzhiwu.yiwu.model.view.CoachMediaVO.CoachMediaVOConverter;
import com.yinzhiwu.yiwu.service.CoachMediaService;
import com.yinzhiwu.yiwu.util.beanutils.Converter;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午4:10:35
*
*/

@RestController
@RequestMapping(value="/api/coachMedia")
public class CoachMediaApiController extends BaseController {
	
	@Autowired CoachMediaService mediaService;
	
	@GetMapping
	@ApiOperation("获取教师多媒体资料")
	public YiwuJson<List<CoachMediaVO>> findVO(
			@ApiParam(value="教练Id", required=true)
			@RequestParam(name="coachId", required=true)
			Integer coachId, 
			@ApiParam(value="多媒体类型，取值:VIDEO,AUDIO,IMAGE,TEXT,FILE, 默认全部", required=false) 
			@RequestParam(name="type", required=false) 
			MediaType type,
			@ApiParam(value="多媒体标签,取值:HEADER(页面头部), DAILY(日常),CERTIFICATE(资质), DANCE(舞蹈), 默认全部", required=false)
			@RequestParam(name="tag", required=false)
			MediaTag tag,
			@ApiParam(value="是否取封面图片,默认值:false") 
			@RequestParam(name="coverage", required=false, defaultValue="false")
			boolean coverage
	){
		
		try {
			CoachMedia media = new CoachMedia();
			EmployeeYzw coach = new EmployeeYzw();
			coach.setId(coachId);
			media.setCoach(coach);
			if(null != type)
				media.setType(type);
			if(null != tag)
				media.setTag(tag);
			media.setCoverage(coverage);
			
			List<CoachMedia> medias = mediaService.findByExample(media);
			List<CoachMediaVO> vos = new ArrayList<>();
			
			Converter<CoachMedia,CoachMediaVO> converter = CoachMediaVOConverter.instance;
			for (CoachMedia m : medias) {
				vos.add(converter.fromPO(m));
			}
			
			return YiwuJson.createBySuccess(vos);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
