package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.CoachMedia;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaTag;
import com.yinzhiwu.yiwu.entity.CoachMedia.MediaType;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午4:14:17
*
*/

@MapedClass(CoachMedia.class)
public class CoachMediaVO {
	
	private Integer id;
	@MapedProperty("coach.id")
	private Integer coachId;
	@MapedProperty("coach.name")
	private String coachName;
	private String uri;
	@MapedProperty(ignored=true)
	private String url;
	private String videoPosterUri;
	@MapedProperty(ignored=true)
	private String videoPosterUrl;
	private String videoTitle;
	private MediaType type;
	private MediaTag tag;
	private String text;
	private boolean coverage;
	
	
	public static class CoachMediaVOConverter  extends AbstractConverter<CoachMedia, CoachMediaVO>{
		public static final CoachMediaVOConverter instance = new CoachMediaVOConverter();
		private static final FileService fileService = SpringUtils.getBean("qiniuServiceImpl");
		
		@Override
		public CoachMediaVO fromPO(CoachMedia po){
			CoachMediaVO vo = super.fromPO(po);
			vo.setUrl(fileService.generateFileUrl(vo.getUri()));
			vo.setVideoPosterUrl(fileService.generateFileUrl(vo.getVideoPosterUri()));
			return vo;
		}
		
	}
	
	public Integer getId() {
		return id;
	}
	public String getUri() {
		return uri;
	}
	public String getUrl() {
		return url;
	}
	public MediaType getType() {
		return type;
	}
	public MediaTag getTag() {
		return tag;
	}
	public String getText() {
		return text;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setType(MediaType type) {
		this.type = type;
	}
	public void setTag(MediaTag tag) {
		this.tag = tag;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getCoachId() {
		return coachId;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public boolean isCoverage() {
		return coverage;
	}

	public void setCoverage(boolean coverage) {
		this.coverage = coverage;
	}

	public String getVideoPosterUri() {
		return videoPosterUri;
	}

	public String getVideoPosterUrl() {
		return videoPosterUrl;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoPosterUri(String videoPosterUri) {
		this.videoPosterUri = videoPosterUri;
	}

	public void setVideoPosterUrl(String videoPosterUrl) {
		this.videoPosterUrl = videoPosterUrl;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
	
}
