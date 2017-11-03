package com.yinzhiwu.yiwu.model.view;

import org.springframework.beans.BeanUtils;

import com.google.common.base.Converter;
import com.yinzhiwu.yiwu.entity.yzw.CourseConnotation;
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

@MapedClass(CourseConnotation.class)
@ApiModel(description="课时内容")
public class CourseConnotationVO {
	
	@MapedProperty
	@ApiModelProperty(value="课时内容")
	private String connotation;
	
	@MapedProperty
	@ApiModelProperty(value="帮助信息")
	private String helpInfomation;
	
	@MapedProperty
	@ApiModelProperty(value="简介")
	private String introduction;
	
	@MapedProperty("pictureUri")
	@ApiModelProperty(value="图片URL")
	private String pictureUrl;
	
	@MapedProperty("videoUri")
	@ApiModelProperty(value="标准视频URL")
	private String videoUrl;
	
	@MapedProperty("videoPosterUri")
	@ApiModelProperty(value="标准视频的Poster图片链接")
	private String videoPosterUrl;
	@ApiModelProperty(value="标准视频的标题")
	private String videoTitle;
	
	@MapedProperty
	@ApiModelProperty("音乐名称")
	private String audioName;
	
	@MapedProperty("audioUri")
	@ApiModelProperty(value="音乐URL")
	private String audioUrl;
	
	@MapedProperty
	@ApiModelProperty(value="舞蹈简介")
	private String danceIntroduction;
	
	
	public static final class CourseConnotationVOConverter extends AbstractConverter<CourseConnotation, CourseConnotationVO>{
		public static final CourseConnotationVOConverter INSTANCE =  new CourseConnotationVOConverter();
		private FileService qiniuService = SpringUtils.getBean("qiniuServiceImpl");

		@Override
		public CourseConnotationVO fromPO(CourseConnotation po) {
			if(null==po) return null;
			CourseConnotationVO  vo =  super.fromPO(po);
			vo.setPictureUrl(qiniuService.generateFileUrl(vo.getPictureUrl()));
		    vo.setAudioUrl(qiniuService.generateFileUrl(vo.getAudioUrl()));
			vo.setVideoUrl(qiniuService.generateFileUrl(vo.getVideoUrl()));
			vo.setVideoPosterUrl(qiniuService.generateFileUrl(vo.getVideoPosterUrl()));
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

	public static CourseConnotationVO fromPO(CourseConnotation po){
		return VOConverter.instance.reverse().convert(po);
	}
	
	public static CourseConnotation toPO(CourseConnotationVO vo){
		return VOConverter.instance.convert(vo);
	}
	
	private static class VOConverter extends Converter<CourseConnotationVO, CourseConnotation>{

	public static final VOConverter instance = new VOConverter();

	@Override
	protected CourseConnotation doForward(CourseConnotationVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CourseConnotationVO doBackward(CourseConnotation po) {
		CourseConnotationVO vo = new CourseConnotationVO();
		BeanUtils.copyProperties(po, vo);
		FileServiceImpl fileService = SpringUtils.getBean(FileServiceImpl.class);
		vo.setPictureUrl(fileService.getFileUrl(po.getPictureUri()));
		vo.setAudioUrl(fileService.getFileUrl(po.getAudioUri()));
		vo.setVideoUrl(fileService.getFileUrl(po.getVideoUri()));
		
		return vo;
	}
	
	}


	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoPosterUrl() {
		return videoPosterUrl;
	}
	public void setVideoPosterUrl(String videoPosterUrl) {
		this.videoPosterUrl = videoPosterUrl;
	}
}
