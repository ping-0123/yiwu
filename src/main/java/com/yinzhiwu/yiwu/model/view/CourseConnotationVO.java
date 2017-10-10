package com.yinzhiwu.yiwu.model.view;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.Connotation;
import com.yinzhiwu.yiwu.service.impl.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午11:57:30
*
*/

@ApiModel(description="课时内容")
public class CourseConnotationVO {
	
	@ApiModelProperty(value="课时内容")
	private String connotation;
	@ApiModelProperty(value="帮助信息")
	private String helpInfomation;
	@ApiModelProperty(value="简介")
	private String introduction;
	@ApiModelProperty(value="图片URL")
	private String pictureUrl;
	@ApiModelProperty(value="标准视频URL")
	private String videoUrl;
	private String audioName;
	@ApiModelProperty(value="音乐URL")
	private String audioUrl;
	@ApiModelProperty(value="舞蹈简介")
	private String danceIntroduction;
	public String getConnotation() {
		return connotation;
	}
	public String getHelpInfomation() {
		return helpInfomation;
	}
	public String getIntroduction() {
		return introduction;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public String getAudioName() {
		return audioName;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public String getDanceIntroduction() {
		return danceIntroduction;
	}
	public void setConnotation(String connotation) {
		this.connotation = connotation;
	}
	public void setHelpInfomation(String helpInfomation) {
		this.helpInfomation = helpInfomation;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public void setDanceIntroduction(String danceIntroduction) {
		this.danceIntroduction = danceIntroduction;
	}

	public static CourseConnotationVO fromPO(Connotation po){
		return VOConverter.instance.reverse().convert(po);
	}
	
	public static Connotation toPO(CourseConnotationVO vo){
		return VOConverter.instance.convert(vo);
	}
	
	private static class VOConverter extends Converter<CourseConnotationVO, Connotation>{

	public static final VOConverter instance = new VOConverter();

	@Override
	protected Connotation doForward(CourseConnotationVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CourseConnotationVO doBackward(Connotation po) {
		CourseConnotationVO vo = new CourseConnotationVO();
		BeanUtils.copyProperties(po, vo);
		FileService fileService = SpringUtils.getBean(FileService.class);
		vo.setPictureUrl(fileService.getFileUrl(po.getPictureUri()));
		vo.setAudioUrl(fileService.getFileUrl(po.getAudioUri()));
		vo.setVideoUrl(fileService.getFileUrl(po.getVideoUri()));
		
		return vo;
	}
	
	}
}
