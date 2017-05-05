package com.yinzhiwu.springmvc3.config;

public class UserDTO {

	private int id ;
	
	private String name;
	
	public UserDTO(){
		id = 1;
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
