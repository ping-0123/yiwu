package com.yinzhiwu.yiwu.entity.yzwOld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vclassroom")
public class ClassRoom {

	@Id
	@Column(length = 32, name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String roomId;

	@Column(length = 32, name = "name")
	private String roomName;

	@Column(name = "store_id")
	private Integer storeId;

	@Column(length = 32)
	private String storeName;

	@Column
	private Integer maxStudentCount;

	@Column
	private Integer minStudentCount;

	private Integer sf_create_user;

	private Integer sf_last_change_user;

	private Date sf_create_time;

	private Date sf_last_change_time;

	private Integer machineCode;

	private Date sf_last_sync_timeStamp;

	private Date sf_last_change_timeStamp;

	public String getRoomId() {
		return roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public Integer getMaxStudentCount() {
		return maxStudentCount;
	}

	public Integer getMinStudentCount() {
		return minStudentCount;
	}

	public Integer getSf_create_user() {
		return sf_create_user;
	}

	public Integer getSf_last_change_user() {
		return sf_last_change_user;
	}

	public Date getSf_create_time() {
		return sf_create_time;
	}

	public Date getSf_last_change_time() {
		return sf_last_change_time;
	}

	public Integer getMachineCode() {
		return machineCode;
	}

	public Date getSf_last_sync_timeStamp() {
		return sf_last_sync_timeStamp;
	}

	public Date getSf_last_change_timeStamp() {
		return sf_last_change_timeStamp;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	public void setMinStudentCount(Integer minStudentCount) {
		this.minStudentCount = minStudentCount;
	}

	public void setSf_create_user(Integer sf_create_user) {
		this.sf_create_user = sf_create_user;
	}

	public void setSf_last_change_user(Integer sf_last_change_user) {
		this.sf_last_change_user = sf_last_change_user;
	}

	public void setSf_create_time(Date sf_create_time) {
		this.sf_create_time = sf_create_time;
	}

	public void setSf_last_change_time(Date sf_last_change_time) {
		this.sf_last_change_time = sf_last_change_time;
	}

	public void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public void setSf_last_sync_timeStamp(Date sf_last_sync_timeStamp) {
		this.sf_last_sync_timeStamp = sf_last_sync_timeStamp;
	}

	public void setSf_last_change_timeStamp(Date sf_last_change_timeStamp) {
		this.sf_last_change_timeStamp = sf_last_change_timeStamp;
	}

}
