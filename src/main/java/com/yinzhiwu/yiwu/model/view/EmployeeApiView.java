package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

/**
*@Author ping
*@Time  创建时间:2017年9月13日下午7:26:54
*
*/

public class EmployeeApiView {

	private int id;
	private String name;
	
	public EmployeeApiView(){}

	public EmployeeApiView(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public EmployeeApiView(EmployeeYzw emp) {
		this.id = emp.getId();
		this.name= emp.getName();
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
