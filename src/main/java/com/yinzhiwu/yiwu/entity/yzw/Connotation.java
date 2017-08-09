package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Embeddable;

@Embeddable
public class Connotation {

	private String connotation;
	private String helpInfomation;
	private String introduction;
	private String pictureNo;
	private String videoUrl;
	private String audioName;
	private String audioUrl;
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

	public String getPictureNo() {
		return pictureNo;
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

	public void setPictureNo(String pictureNo) {
		this.pictureNo = pictureNo;
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

}
