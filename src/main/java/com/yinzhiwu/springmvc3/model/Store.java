package com.yinzhiwu.springmvc3.model;

import com.yinzhiwu.springmvc3.entity.StoreInfo;

public class Store {
	
	private int id;
	
	private String name;
	
	private String address;
	
	private String telePhone;
	
	public Store(){
		
	}
	public Store(int id, String name, String address, String telePhone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telePhone = telePhone;
	}
	
	public Store(StoreInfo si){
		this.id = si.getStoreId();
		this.address = si.getAddress();
		this.telePhone = si.getTelePhone();
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

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final String getTelePhone() {
		return telePhone;
	}

	public final void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	
	
}
