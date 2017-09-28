package com.yinzhiwu.yiwu.model.view;

/**
*@Author ping
*@Time  创建时间:2017年9月28日上午10:50:07
*
*/

public class ResourceZTreeApiView {
	
	private Integer id;
	private Integer pId;
	private String name;
	private boolean checked;
	
	
	
	public ResourceZTreeApiView(Integer id, Integer pId, String name, boolean checked) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getpId() {
		return pId;
	}
	public String getName() {
		return name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
