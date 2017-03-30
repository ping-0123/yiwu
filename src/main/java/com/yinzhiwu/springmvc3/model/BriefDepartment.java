package com.yinzhiwu.springmvc3.model;

import com.yinzhiwu.springmvc3.entity.Department;

public class BriefDepartment {

	private int id;
	
	private String name;
	

	public BriefDepartment(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BriefDepartment(Department d){
		this.id = d.getId();
		this.name = d.getDeptName();
	}


	public String getName() {
		return name;
	}


	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
