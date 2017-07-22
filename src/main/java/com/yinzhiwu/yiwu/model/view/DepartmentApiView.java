package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.yzwOld.Department;

public class DepartmentApiView {

	private int id;
	
	private String name;
	

	public DepartmentApiView(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public DepartmentApiView(Department d){
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
