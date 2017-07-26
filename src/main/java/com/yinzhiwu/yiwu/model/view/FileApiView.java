package com.yinzhiwu.yiwu.model.view;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午8:03:30
*
*/

public class FileApiView {

	private int returnCode;
	private String name;
	private String url;
	
	
	public int getReturnCode() {
		return returnCode;
	}
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public FileApiView(int returnCode, String name, String url) {
		this.returnCode = returnCode;
		this.name = name;
		this.url = url;
	}
	public FileApiView() {
	}
	
	
}
