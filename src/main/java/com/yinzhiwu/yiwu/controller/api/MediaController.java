package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Media;
import com.yinzhiwu.yiwu.entity.Media.MediaTag;
import com.yinzhiwu.yiwu.entity.Media.MediaType;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.MediaVO;
import com.yinzhiwu.yiwu.service.MediaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午4:10:35
*
*/

@RestController
@RequestMapping(value="/api/multiMedia")
public class MediaController extends BaseController {
	
	@Autowired MediaService mediaService;
	
	@GetMapping
	@ApiOperation("获取教师多媒体资料")
	public YiwuJson<List<MediaVO>> findVO(
			@ApiParam(value="教练Id", required=true)
			@RequestParam(name="coachId", required=true)
			Integer coachId, 
			@ApiParam(value="多媒体类型，取值:VIDEO,AUDIO,IMAGE(默认),TEXT,FILE", required=false) 
			@RequestParam(name="type", required=false, defaultValue="IMAGE") 
			MediaType type,
			@ApiParam(value="多媒体标签,取值:DAILY(日常),CERTIFICATE(资质)(默认), DANCE(舞蹈)", required=false)
			@RequestParam(name="tag", required=false, defaultValue="CERTIFICATE")
			MediaTag tag,
			@ApiParam(value="是否取封面图片,默认值:false") 
			@RequestParam(name="coverage", required=false, defaultValue="false")
			boolean coverage
	){
		
		try {
			Media media = new Media();
			EmployeeYzw coach = new EmployeeYzw();
			coach.setId(coachId);
			media.setCoach(coach);
			media.setType(type);
			media.setTag(tag);
			media.setCoverage(coverage);
			
			List<Media> medias = mediaService.findByExample(media);
			List<MediaVO> vos = new ArrayList<>();
			for (Media m : medias) {
				vos.add(MediaVO.fromDAO(m));
			}
			
			return YiwuJson.createBySuccess(vos);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
}
