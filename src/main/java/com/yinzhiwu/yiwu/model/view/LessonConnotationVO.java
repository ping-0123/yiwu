package com.yinzhiwu.yiwu.model.view;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.impl.FileServiceImpl;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月10日上午11:57:30
*
*/

@MapedClass(LessonConnotation.class)
@ApiModel(description="课时内容")
public class LessonConnotationVO{
	
	@MapedProperty
	@ApiModelProperty(value="课时内容")
	private String connotation;
	
	@MapedProperty
	@ApiModelProperty(value="帮助信息")
	private String helpInfomation;
	
	@MapedProperty
	@ApiModelProperty(value="简介")
	private String introduction;
	
	@MapedProperty(value="pictureUri")
	@ApiModelProperty(value="图片URL")
	private String pictureUrl;
	
	@MapedProperty(value="standardVideoUri")
	@ApiModelProperty(value="标准视频URL")
	private String standardVideoUrl;
	@MapedProperty(value="standardVideoPosterUri")
	@ApiModelProperty(value="标准视频poster url")
	private String standardVideoPosterUrl;
	@ApiModelProperty(value="标准视频标题")
	private String standardVideoTitle;
	
	@MapedProperty
	private String audioName;
	
	@MapedProperty(value="audioUri")
	@ApiModelProperty(value="音乐URL")
	private String audioUrl;
	
	@MapedProperty
	@ApiModelProperty(value="舞蹈简介")
	private String danceIntroduction;
	
	@MapedProperty(value="puzzleVideoUri")
	@ApiModelProperty(value="疑难点解析视频URL")
	private String puzzleVideoUrl;
	
	@MapedProperty(value="puzzleVideoPosterUri")
	@ApiModelProperty(value="疑难点解析视频poster url")
	private String puzzleVideoPosterUrl;
	
	@ApiModelProperty(value="疑难点解析视频标题")
	private String puzzleVideoTitle;
	
	
	@MapedProperty("practicalVideoUri")
	@ApiModelProperty(value="上课实际视频URL")
	private String practicalVideoUrl;
	
	@ApiModelProperty(value="上课实际视频 poster url")
	@MapedProperty("practicalVideoPosterUri")
	private String practicalVideoPosterUrl;
	
	@ApiModelProperty(value="上课实际视频的标题")
	private String practicalVideoTitle;
	
	public static final class LessonConnotationVOConverter extends AbstractConverter<LessonConnotation, LessonConnotationVO>{
		public final static LessonConnotationVOConverter INSTANCE = new LessonConnotationVOConverter();
		
		private final static FileService qiniuService = SpringUtils.getBean("qiniuServiceImpl");

		@Override
		public LessonConnotationVO fromPO(LessonConnotation po) {
			LessonConnotationVO vo =  super.fromPO(po);
			vo.setPictureUrl(qiniuService.generateFileUrl(vo.getPictureUrl()));
			vo.setAudioUrl(qiniuService.generateFileUrl(vo.getAudioUrl()));
			vo.setStandardVideoUrl(qiniuService.generateFileUrl(vo.getStandardVideoUrl()));
			vo.setStandardVideoPosterUrl(qiniuService.generateFileUrl(vo.getStandardVideoPosterUrl()));
			vo.setPuzzleVideoUrl(qiniuService.generateFileUrl(vo.getPuzzleVideoUrl()));
			vo.setPuzzleVideoPosterUrl(qiniuService.generateFileUrl(vo.getPuzzleVideoPosterUrl()));
			vo.setPracticalVideoUrl(qiniuService.generateFileUrl(vo.getPracticalVideoUrl()));
			vo.setPracticalVideoPosterUrl(qiniuService.generateFileUrl(vo.getPracticalVideoPosterUrl()));
			return vo;
		}
		
		
	}
	
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
		FileServiceImpl fileService = SpringUtils.getBean(FileServiceImpl.class);
		vo.setPictureUrl(fileService.getFileUrl(po.getPictureUri()));
		vo.setAudioUrl(fileService.getFileUrl(po.getAudioUri()));
		vo.setPracticalVideoUrl(fileService.getFileUrl(po.getPracticalVideoUri()));
		vo.setPuzzleVideoUrl(fileService.getFileUrl(po.getPuzzleVideoUri()));
		vo.setStandardVideoPosterUrl(fileService.getFileUrl(po.getStandardVideoUri()));
		
		return vo;
	}
	
	}

	public String getStandardVideoUrl() {
		return standardVideoUrl;
	}
	public String getStandardVideoPosterUrl() {
		return standardVideoPosterUrl;
	}
	public String getStandardVideoTitle() {
		return standardVideoTitle;
	}
	public String getPuzzleVideoPosterUrl() {
		return puzzleVideoPosterUrl;
	}
	public String getPuzzleVideoTitle() {
		return puzzleVideoTitle;
	}
	public String getPracticalVideoPosterUrl() {
		return practicalVideoPosterUrl;
	}
	public String getPracticalVideoTitle() {
		return practicalVideoTitle;
	}
	public void setStandardVideoUrl(String standardVideoUrl) {
		this.standardVideoUrl = standardVideoUrl;
	}
	public void setStandardVideoPosterUrl(String standardVideoPosterUrl) {
		this.standardVideoPosterUrl = standardVideoPosterUrl;
	}
	public void setStandardVideoTitle(String standardVideoTitle) {
		this.standardVideoTitle = standardVideoTitle;
	}
	public void setPuzzleVideoPosterUrl(String puzzleVideoPosterUrl) {
		this.puzzleVideoPosterUrl = puzzleVideoPosterUrl;
	}
	public void setPuzzleVideoTitle(String puzzleVideoTitle) {
		this.puzzleVideoTitle = puzzleVideoTitle;
	}
	public void setPracticalVideoPosterUrl(String practicalVideoPosterUrl) {
		this.practicalVideoPosterUrl = practicalVideoPosterUrl;
	}
	public void setPracticalVideoTitle(String practicalVideoTitle) {
		this.practicalVideoTitle = practicalVideoTitle;
	}
}
