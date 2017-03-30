package com.yinzhiwu.springmvc3.entity;

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
	@Column(length=32,name="roomId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String roomId;
	
	@Column(length=32)
	private String roomName;
	
	@Column
	private Integer storeId;
	
	@Column(length=32)
	private String storeName;
	
	@Column
	private Integer maxStudentCount;
	
	@Column
	private Integer minStudentCount;
	

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


	
	
}
