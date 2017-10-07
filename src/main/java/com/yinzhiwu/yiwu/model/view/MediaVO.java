package com.yinzhiwu.yiwu.model.view;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.Media;
import com.yinzhiwu.yiwu.entity.Media.MediaTag;
import com.yinzhiwu.yiwu.entity.Media.MediaType;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.service.impl.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;

/**
*@Author ping
*@Time  创建时间:2017年10月7日下午4:14:17
*
*/

public class MediaVO {
	
	private Integer id;
	private Integer coachId;
	private String coachName;
	private String uri;
	private String url;
	private MediaType type;
	private MediaTag tag;
	private String text;
	private boolean coverage;
	
	public static MediaVO fromDAO(Media dao){
		return VOConverter.instance.reverse().convert(dao);
	}
	
	public static Media toDAO(MediaVO vo){
		return VOConverter.instance.convert(vo);
	}
	
	private static class VOConverter  extends Converter<MediaVO, Media>{
		public static final VOConverter instance = new VOConverter();
		
		@Override
		protected Media doForward(MediaVO vo) {
			Media media = new Media();
			BeanUtils.copyProperties(vo, media);
			if(vo.getCoachId() !=null){
				EmployeeYzw coach = new EmployeeYzw();
				coach.setId(vo.getCoachId());
				media.setCoach(coach);
			}
			return media;
		}

		@Override
		protected MediaVO doBackward(Media media) {
			MediaVO vo = new MediaVO();
			BeanUtils.copyProperties(media, vo);
			FileService fileService=SpringUtils.getBean(FileService.class);
			vo.setUrl(fileService.getFileUrl(vo.getUri()));
			if(media.getCoach() !=null){
				vo.setCoachId(media.getCoach().getId());
				vo.setCoachName(media.getCoach().getName());
			}
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
	
	
}
