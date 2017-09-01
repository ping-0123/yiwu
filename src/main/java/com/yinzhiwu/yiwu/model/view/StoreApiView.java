package com.yinzhiwu.yiwu.model.view;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yinzhiwu.yiwu.entity.StoreInfo;
import com.yinzhiwu.yiwu.entity.yzwOld.Department;


@JsonInclude(value= Include.NON_NULL)
public class StoreApiView {

	private int id;

	private String name;

	private String address;

	private String telePhone;
	private Integer districtId;

	public StoreApiView() {

	}

	public StoreApiView(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public StoreApiView(int id, String name, String address, String telePhone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telePhone = telePhone;
	}
	
	

	public StoreApiView(StoreInfo si) {
		Assert.notNull(si);

		this.id = si.getId();
		this.name = si.getName();
		if(si.getAddress() != null)
			this.address = si.getAddress().getAddress();
		this.telePhone = si.getTelePhone();
	}

	public StoreApiView(Department d) {
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

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public StoreApiView(int id, String name, Integer districtId) {
		this.id = id;
		this.name = name;
		this.districtId = districtId;
	}

}
