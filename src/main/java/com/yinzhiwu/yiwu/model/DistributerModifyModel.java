package com.yinzhiwu.yiwu.model;

import com.yinzhiwu.yiwu.entity.Distributer;

public class DistributerModifyModel {

	private String nickName;
	
	private String name;
	
	private String phoneNo;

	public String getNickName() {
		return nickName;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public Distributer toDistributer(){
		Distributer d = new Distributer();
		d.setName(name);
		d.setNickName(nickName);
		d.setPhoneNo(phoneNo);
		return d;
	}
}
