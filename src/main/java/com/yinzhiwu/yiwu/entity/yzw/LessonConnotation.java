package com.yinzhiwu.yiwu.entity.yzw;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月9日上午10:24:26
*
*/

@ApiModel(description="课时内容")
public class LessonConnotation {
	
	@ApiModelProperty(value="课时内容")
	private String connotation;
	@ApiModelProperty(value="帮助信息")
	private String helpInfomation;
	@ApiModelProperty(value="简介")
	private String introduction;
	@ApiModelProperty(value="图片URI")
	private String pictureUri;
	@ApiModelProperty(value="标准视频URI")
	private String standardVideoUri;
	private String standardVideoPosterUri;
	private String standardVideoTitle;
	private String audioName;
	@ApiModelProperty(value="音乐URI")
	private String audioUri;
	@ApiModelProperty(value="舞蹈简介")
	private String danceIntroduction;
	@ApiModelProperty(value="疑难点解析视频URI")
	private String puzzleVideoUri;
	private String puzzleVideoPosterUri;
	private String puzzleVideoTitle;
	@ApiModelProperty(value="上课实际视频URI")
	private String practicalVideoUri;
	private String practicalVideoPosterUri;
	private String practicalVideoTitle;
	
	public String getPuzzleVideoUri() {
		return puzzleVideoUri;
	}
	public String getPracticalVideoUri() {
		return practicalVideoUri;
	}
	public void setPuzzleVideoUri(String puzzleVideoUri) {
		this.puzzleVideoUri = puzzleVideoUri;
	}
	public void setPracticalVideoUri(String practicalVideoUri) {
		this.practicalVideoUri = practicalVideoUri;
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
	public String getPictureUri() {
		return pictureUri;
	}
	public String getAudioName() {
		return audioName;
	}
	public String getAudioUri() {
		return audioUri;
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
	public void setPictureUri(String pictureUri) {
		this.pictureUri = pictureUri;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	public void setAudioUri(String audioUri) {
		this.audioUri = audioUri;
	}
	public void setDanceIntroduction(String danceIntroduction) {
		this.danceIntroduction = danceIntroduction;
	}
	public String getStandardVideoUri() {
		return standardVideoUri;
	}
	public void setStandardVideoUri(String standardVideoUri) {
		this.standardVideoUri = standardVideoUri;
	}
	public String getStandardVideoPosterUri() {
		return standardVideoPosterUri;
	}
	public String getStandardVideoTitle() {
		return standardVideoTitle;
	}
	public String getPuzzleVideoPosterUri() {
		return puzzleVideoPosterUri;
	}
	public String getPuzzleVideoTitle() {
		return puzzleVideoTitle;
	}
	public String getPracticalVideoPosterUri() {
		return practicalVideoPosterUri;
	}
	public String getPracticalVideoTitle() {
		return practicalVideoTitle;
	}
	public void setStandardVideoPosterUri(String standardVideoPosterUri) {
		this.standardVideoPosterUri = standardVideoPosterUri;
	}
	public void setStandardVideoTitle(String standardVideoTitle) {
		this.standardVideoTitle = standardVideoTitle;
	}
	public void setPuzzleVideoPosterUri(String puzzleVideoPosterUri) {
		this.puzzleVideoPosterUri = puzzleVideoPosterUri;
	}
	public void setPuzzleVideoTitle(String puzzleVideoTitle) {
		this.puzzleVideoTitle = puzzleVideoTitle;
	}
	public void setPracticalVideoPosterUri(String practicalVideoPosterUri) {
		this.practicalVideoPosterUri = practicalVideoPosterUri;
	}
	public void setPracticalVideoTitle(String practicalVideoTitle) {
		this.practicalVideoTitle = practicalVideoTitle;
	}
	
	
}
