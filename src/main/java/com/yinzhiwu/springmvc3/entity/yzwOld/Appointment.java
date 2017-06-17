package com.yinzhiwu.springmvc3.entity.yzwOld;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vappointment")
public class Appointment {
	
	public enum APPOINT_STATUS{APPONTED,UN_APOINTED}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer coursehourId;
	
	@Column
	private Integer customerId;
	
	@Column(length=4, name="status")
	private String status;
	
	@JsonIgnore
	@Column(name="sf_create_user")
	private Integer createUserId;
	
	@JsonIgnore
	@Column(name="sf_last_change_user")
	private Integer lastChangeUserId;
	
	@JsonIgnore
	@Column(name="sf_create_time")
	private  Date createTime;
	
	@JsonIgnore
	@Column(name="sf_last_change_time")
	private Date lastChangeTime;
	
	@JsonIgnore
	@Column
	private Integer machinecode;
	
	@JsonIgnore
	@Column(name="sf_Last_Sync_TimeStamp")
	private Date lastSyncTimeStamp;
	
	

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getCoursehourId() {
		return coursehourId;
	}

	public final void setCoursehourId(Integer coursehourId) {
		this.coursehourId = coursehourId;
	}

	public final Integer getCustomerId() {
		return customerId;
	}

	public final void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	public final Integer getCreateUserId() {
		return createUserId;
	}

	public final void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public final Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public final void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Date getLastChangeTime() {
		return lastChangeTime;
	}

	public final void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public final Integer getMachinecode() {
		return machinecode;
	}

	public final void setMachinecode(Integer machinecode) {
		this.machinecode = machinecode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}
	
	
}
