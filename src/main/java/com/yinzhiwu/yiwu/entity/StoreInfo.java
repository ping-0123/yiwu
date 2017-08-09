package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "yiwu_store_info")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class StoreInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9152899676121565928L;

	@Column
	private String name;
	
	@Column
	@Embedded
	private Address address;

	@Column(length = 20)
	private String telePhone;



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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
