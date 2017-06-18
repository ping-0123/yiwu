package com.yinzhiwu.springmvc3.model.view;

import com.yinzhiwu.springmvc3.entity.yzw.EmployeeYzw;
import com.yinzhiwu.springmvc3.entity.yzwOld.Employee;

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

	public EmployeeApiView(EmployeeYzw emp) {
		this.id = emp.getId();
		this.name = emp.getName();
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
