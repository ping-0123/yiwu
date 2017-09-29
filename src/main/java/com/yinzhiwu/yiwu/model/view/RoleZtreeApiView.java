package com.yinzhiwu.yiwu.model.view;

/**
*@Author ping
*@Time  创建时间:2017年9月29日下午5:17:45
*
*/

public class RoleZtreeApiView {
	
	private Integer id;
	private String name;
	private boolean checked;
	
	
	
	public RoleZtreeApiView(Integer id, String name, boolean checked) {
		this.id = id;
		this.name = name;
		this.checked = checked;
	}
	public Integer getId() {
		return id;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
