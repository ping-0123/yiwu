package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Embeddable;

@Embeddable
public class Connotation {

	private String connotation;
	private String helpInfomation;
	private String introduction;
	private String pictureUri;
	private String videoUri;
	private String videoPosterUri;
	private String videoTitle;
	private String audioName;
	private String audioUri;
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

	public String getVideoPosterUri() {
		return videoPosterUri;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoPosterUri(String videoPosterUri) {
		this.videoPosterUri = videoPosterUri;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
	

}
