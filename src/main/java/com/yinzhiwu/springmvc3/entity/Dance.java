package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vdance")
public class Dance {

	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@Column(length=32)
	private String name;
	
	@Column(length=32)
	private String danceClass;
	
	@Column
	private Float remuneration;
	
	@Column(name="sf_create_user")
	private Integer createUserId;
	
	@Column(name="sf_last_change_user")
	private Integer lastChangeUserId;
	
	@Column(name="sf_create_time")
	private Date createTime;
	
	@Column(name="sf_last_change_time")
	private Date lastChangeTime;
	
	private Integer machineCode;
	
	private Date sf_last_sync_timeStamp;
	
	private Date sf_last_change_timeStamp;
	

	public Dance() {
	}

	public Dance(String id, String name, String danceClass) {
		this.id = id;
		this.name = name;
		this.danceClass = danceClass;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDanceClass() {
		return danceClass;
	}

	public final void setDanceClass(String danceClass) {
		this.danceClass = danceClass;
	}

	public final Float getRemuneration() {
		return remuneration;
	}

	public final void setRemuneration(Float remuneration) {
		this.remuneration = remuneration;
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

	public Integer getMachineCode() {
		return machineCode;
	}

	public Date getSf_last_sync_timeStamp() {
		return sf_last_sync_timeStamp;
	}

	public Date getSf_last_change_timeStamp() {
		return sf_last_change_timeStamp;
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
