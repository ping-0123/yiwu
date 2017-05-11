package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vclassroom")
public class ClassRoom {

	@Id
	@Column(length=32,name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String roomId;
	
	@Column(length=32, name="name")
	private String roomName;
	
	@Column(name="store_id")
	private Integer storeId;
	
	@Column(length=32)
	private String storeName;
	
	@Column
	private Integer maxStudentCount;
	
	@Column
	private Integer minStudentCount;
	
	private int sf_create_user;
	
	private int sf_last_change_user;
	
	private Date sf_create_time;
	
	private Date sf_last_change_time;
	
	private Integer machineCode;
	
	private Date sf_last_sync_timeStamp;
	
	private Date  sf_last_change_timeStamp;
	

	public final String getRoomId() {
		return roomId;
	}

	public final void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public final String getRoomName() {
		return roomName;
	}

	public final void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public final Integer getStoreId() {
		return storeId;
	}

	public final void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public final String getStoreName() {
		return storeName;
	}

	public final void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public final Integer getMaxStudentCount() {
		return maxStudentCount;
	}

	public final void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	public final Integer getMinStudentCount() {
		return minStudentCount;
	}

	public final void setMinStudentCount(Integer minStudentCount) {
		this.minStudentCount = minStudentCount;
	}

	public int getSf_create_user() {
		return sf_create_user;
	}

	public int getSf_last_change_user() {
		return sf_last_change_user;
	}

	public Date getSf_create_time() {
		return sf_create_time;
	}

	public Date getSf_last_change_time() {
		return sf_last_change_time;
	}

	public int getMachineCode() {
		return machineCode;
	}

	public Date getSf_last_sync_timeStamp() {
		return sf_last_sync_timeStamp;
	}

	public Date getSf_last_change_timeStamp() {
		return sf_last_change_timeStamp;
	}

	public void setSf_create_user(int sf_create_user) {
		this.sf_create_user = sf_create_user;
	}

	public void setSf_last_change_user(int sf_last_change_user) {
		this.sf_last_change_user = sf_last_change_user;
	}

	public void setSf_create_time(Date sf_create_time) {
		this.sf_create_time = sf_create_time;
	}

	public void setSf_last_change_time(Date sf_last_change_time) {
		this.sf_last_change_time = sf_last_change_time;
	}

	public void setMachineCode(int machineCode) {
		this.machineCode = machineCode;
	}

	public void setSf_last_sync_timeStamp(Date sf_last_sync_timeStamp) {
		this.sf_last_sync_timeStamp = sf_last_sync_timeStamp;
	}

	public void setSf_last_change_timeStamp(Date sf_last_change_timeStamp) {
		this.sf_last_change_timeStamp = sf_last_change_timeStamp;
	}


	
	
}
