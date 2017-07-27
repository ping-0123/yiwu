package com.yinzhiwu.yiwu.web.purchase.dto;

import javax.validation.constraints.NotNull;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午3:51:05
*
*/

public class StoreDto {
	private Integer id;
	private String name;
	private Boolean isDefault;
	
	public static StoreDto fromDepartment(@NotNull DepartmentYzw dept){
		if(dept == null) throw new IllegalArgumentException("dept cannot be null");
		StoreDto view =  new StoreDto();
		view.setId(dept.getId());
		view.setName(dept.getName());
		return view;
	}
	
	public StoreDto(){}
	public StoreDto(DepartmentYzw dept){
		if(dept == null) throw new IllegalArgumentException("dept cannot be null");
		this.id= dept.getId();
		this.name = dept.getName();
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

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
