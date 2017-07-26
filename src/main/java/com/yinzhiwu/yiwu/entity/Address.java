package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(length = 32)
	private String nation;
	@Column(length = 32)
	private String province;
	@Column(length = 32)
	private String city;
	@Column(length = 32)
	private String district;
	@Column(length = 32)
	private String town;
	@Column(length = 32)
	private String village;
	@Column(length = 128)
	private String address;
	private Float longitude;
	private Float latitude;

	public Address() {
	}

	public Address(String contry, String province, String city, String district, String town, String village,
			String detail, float longitude, float latitude) {
		super();
		this.nation = contry;
		this.province = province;
		this.city = city;
		this.district = district;
		this.town = town;
		this.village = village;
		this.address = detail;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getTown() {
		return town;
	}

	public String getVillage() {
		return village;
	}

	public Float getLongitude() {
		return longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
