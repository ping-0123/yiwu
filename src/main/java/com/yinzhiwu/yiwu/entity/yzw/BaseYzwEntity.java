package com.yinzhiwu.yiwu.entity.yzw;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.context.ThreadContext;
import com.yinzhiwu.yiwu.entity.Distributer;


@MappedSuperclass
public abstract class BaseYzwEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1232031968327117427L;

	@JsonIgnore
	@Column(updatable = false, name = "sf_create_user")
	private Integer createUserId;

	@JsonIgnore
	@Column(name = "sf_last_change_user")
	private Integer lastChangeUserId;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "sf_create_time", insertable = true, updatable = false)
	private Date createTime;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "sf_last_change_time")
	private Date lastChangeTime;

	@JsonIgnore
	@Column
	private Integer machineCode;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "sf_last_sync_timeStamp")
	private Date lastSyncTimeStamp;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "sf_last_change_timeStamp")
	private Date lastChangeTimestamp;

	public BaseYzwEntity() {
	}

	public void init() {
		Date date = new Date();
		this.createTime = date;
		this.lastChangeTime = date;
		this.lastChangeTimestamp = date;
		this.lastSyncTimeStamp = date;
		Distributer distributer = ThreadContext.getUser();
		if(distributer != null){
			this.createUserId = distributer.getId();
			this.lastChangeUserId = distributer.getId();
		}else{
			this.createUserId = 1;
			this.lastChangeUserId = 1;
		}
	}

	public void beforeUpdate() {
		Date date = new Date();
		this.lastChangeTime = date;
		this.lastChangeTimestamp = date;
		this.lastSyncTimeStamp = date;
		Distributer distributer = ThreadContext.getUser();
		if(distributer != null)
			this.lastChangeUserId = distributer.getId();
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public Integer getMachineCode() {
		return machineCode;
	}

	public Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public Date getLastChangeTimestamp() {
		return lastChangeTimestamp;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}

	public void setLastChangeTimestamp(Date lastChangeTimestamp) {
		this.lastChangeTimestamp = lastChangeTimestamp;
	}

	public Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

}
