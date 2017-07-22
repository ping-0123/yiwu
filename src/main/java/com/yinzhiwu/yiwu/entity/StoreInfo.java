package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yiwu_store_info")
public class StoreInfo {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer storeId;
	
	@Column
	@Embedded
	private Address address;
	
	@Column(length=20)
	private String telePhone;

	public final Integer getStoreId() {
		return storeId;
	}

	public final void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}


	public final String getTelePhone() {
		return telePhone;
	}

	public final void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
