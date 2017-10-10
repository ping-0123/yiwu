package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="课程内容")
@Embeddable
public class Connotation {

	@ApiModelProperty(value="课程内容")
	private String connotation;
	@ApiModelProperty(value="帮助信息")
	private String helpInfomation;
	@ApiModelProperty(value="简介")
	private String introduction;
	@ApiModelProperty(value="图片URI")
	private String pictureUri;
	@ApiModelProperty(value="标准视频URI")
	private String videoUri;
	private String audioName;
	@ApiModelProperty(value="音乐URI")
	private String audioUri;
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


	public String getAudioName() {
		return audioName;
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

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	public void setDanceIntroduction(String danceIntroduction) {
		this.danceIntroduction = danceIntroduction;
	}

	public String getPictureUri() {
		return pictureUri;
	}

	public String getVideoUri() {
		return videoUri;
	}

	public String getAudioUri() {
		return audioUri;
	}

	public void setPictureUri(String pictureUri) {
		this.pictureUri = pictureUri;
	}

	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}

	public void setAudioUri(String audioUri) {
		this.audioUri = audioUri;
	}

}
