package com.yinzhiwu.yiwu.model.view;

/**
*@Author ping
*@Time  创建时间:2017年9月13日下午7:23:59
*
*/


public class DepartmentApiView {
	
	private int id;
	private String name;
	
	public DepartmentApiView(){}
	public DepartmentApiView(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
