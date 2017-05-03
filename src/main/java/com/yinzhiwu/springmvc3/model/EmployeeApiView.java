package com.yinzhiwu.springmvc3.model;

import com.yinzhiwu.springmvc3.entity.Employee;

public class EmployeeApiView {

	private int id;
	
	private String name;

	public EmployeeApiView(Employee e){
		this.id = e.getId();
		this.name= e.getName();
	}
	
	public EmployeeApiView() {
	}

	public EmployeeApiView(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
	
}
