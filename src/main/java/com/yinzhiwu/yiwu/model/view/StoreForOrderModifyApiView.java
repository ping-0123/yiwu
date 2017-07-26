package com.yinzhiwu.yiwu.model.view;

import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午3:51:05
*
*/

public class StoreForOrderModifyApiView {
	private int id;
	private String name;
	
	public static StoreForOrderModifyApiView fromDepartment(@NotNull DepartmentYzw dept){
		if(dept == null) throw new IllegalArgumentException("dept cannot be null");
		StoreForOrderModifyApiView view =  new StoreForOrderModifyApiView();
		view.setId(dept.getId());
		view.setName(dept.getName());
		return view;
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
