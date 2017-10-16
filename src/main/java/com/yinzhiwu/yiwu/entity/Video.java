package com.yinzhiwu.yiwu.entity;

import javax.persistence.Embeddable;

/**
*@Author ping
*@Time  创建时间:2017年10月16日上午10:41:34
*
*/

@Embeddable
public class Video {
	
	private String titile;
	private String uri;
	private String posterUri;
	
	
	public String getTitile() {
		return titile;
	}
	public String getUri() {
		return uri;
	}
	public String getPosterUri() {
		return posterUri;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setPosterUri(String posterUri) {
		this.posterUri = posterUri;
	}
	
	
}
