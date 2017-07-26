package com.yinzhiwu.yiwu.model;

import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.entity.StoreInfo;
import com.yinzhiwu.yiwu.entity.yzwOld.Department;

public class Store {

	private int id;

	private String name;

	private String address;

	private String telePhone;

	public Store() {

	}

	public Store(int id, String name, String address, String telePhone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telePhone = telePhone;
	}

	public Store(StoreInfo si) {
		Assert.notNull(si);
		Assert.notNull(si.getAddress());

		this.id = si.getStoreId();
		this.address = si.getAddress().getAddress();
		this.telePhone = si.getTelePhone();
	}

	public Store(Department d) {
		this.id = d.getId();
		this.name = d.getDeptName();
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
