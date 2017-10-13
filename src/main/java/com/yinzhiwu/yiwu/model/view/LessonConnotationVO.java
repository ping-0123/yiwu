package com.yinzhiwu.yiwu.model.view;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;
import com.yinzhiwu.yiwu.service.impl.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanClass;
import com.yinzhiwu.yiwu.util.convert.annotation.BeanProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午11:57:30
*
*/

@BeanClass(LessonConnotation.class)
@ApiModel(description="课时内容")
public class LessonConnotationVO {
	
	@BeanProperty
	@ApiModelProperty(value="课时内容")
	private String connotation;
	
	@BeanProperty
	@ApiModelProperty(value="帮助信息")
	private String helpInfomation;
	
	@BeanProperty
	@ApiModelProperty(value="简介")
	private String introduction;
	
	@BeanProperty(value="pictureUri")
	@ApiModelProperty(value="图片URL")
	private String pictureUrl;
	
	@BeanProperty(value="videoUri")
	@ApiModelProperty(value="标准视频URL")
	private String videoUrl;
	
	@BeanProperty
	private String audioName;
	
	@BeanProperty(value="audioUri")
	@ApiModelProperty(value="音乐URL")
	private String audioUrl;
	
	@BeanProperty
	@ApiModelProperty(value="舞蹈简介")
	private String danceIntroduction;
	
	@BeanProperty(value="puzzleVideoUri")
	@ApiModelProperty(value="疑难点解析视频URL")
	private String puzzleVideoUrl;
	
	@BeanProperty("practicalVideoUri")
	@ApiModelProperty(value="上课实际视频URL")
	private String practicalVideoUrl;
	
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
	public String getPuzzleVideoUrl() {
		return puzzleVideoUrl;
	}
	public String getPracticalVideoUrl() {
		return practicalVideoUrl;
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
	public void setPuzzleVideoUrl(String puzzleVideoUrl) {
		this.puzzleVideoUrl = puzzleVideoUrl;
	}
	public void setPracticalVideoUrl(String practicalVideoUrl) {
		this.practicalVideoUrl = practicalVideoUrl;
	}

	public static LessonConnotationVO fromPO(LessonConnotation po){
		return VOConverter.instance.reverse().convert(po);
	}
	
	public static LessonConnotation toPO(LessonConnotationVO vo){
		return VOConverter.instance.convert(vo);
	}
	
	private static class VOConverter extends Converter<LessonConnotationVO, LessonConnotation>{

	public static final VOConverter instance = new VOConverter();

	@Override
	protected LessonConnotation doForward(LessonConnotationVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LessonConnotationVO doBackward(LessonConnotation po) {
		LessonConnotationVO vo = new LessonConnotationVO();
		BeanUtils.copyProperties(po, vo);
		FileService fileService = SpringUtils.getBean(FileService.class);
		vo.setPictureUrl(fileService.getFileUrl(po.getPictureUri()));
		vo.setAudioUrl(fileService.getFileUrl(po.getAudioUri()));
		vo.setPracticalVideoUrl(fileService.getFileUrl(po.getPracticalVideoUri()));
		vo.setPuzzleVideoUrl(fileService.getFileUrl(po.getPuzzleVideoUri()));
		vo.setVideoUrl(fileService.getFileUrl(po.getVideoUri()));
		
		return vo;
	}
	
	}
}
