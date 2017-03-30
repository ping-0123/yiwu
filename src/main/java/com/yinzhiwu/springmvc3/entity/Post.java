package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vpost")
public class Post {

	
	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer type;
	
	@Column(length=50)
	private String name;
	
	@Column(length=200)
	private String description;
	
	@Column
	private Integer creator;
	
	@Column
	private Integer lastChanger;
	
	@Column
	private Date createTime;
	
	@Column
	private Date lastChangeTime;
	
	@Column
	private Integer removed;
	
	@Column
	private Integer flag;
	
	@Column
	private Integer wparam;
	
	@Column
	private Integer lparam;
	
	@Column
	private Integer machineCode;
	
	@Column(name="Sf_Last_Sync_TimeStamp")
	private Date lastSyncTimeStamp;
	
	@Column(name="SF_Last_Change_Timestamp")
	private Date lastChangeTimeStamp;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getType() {
		return type;
	}

	public final void setType(Integer type) {
		this.type = type;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final Integer getCreator() {
		return creator;
	}

	public final void setCreator(Integer creator) {
		this.creator = creator;
	}

	public final Integer getLastChanger() {
		return lastChanger;
	}

	public final void setLastChanger(Integer lastChanger) {
		this.lastChanger = lastChanger;
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

	public final Integer getRemoved() {
		return removed;
	}

	public final void setRemoved(Integer removed) {
		this.removed = removed;
	}

	public final Integer getFlag() {
		return flag;
	}

	public final void setFlag(Integer flag) {
		this.flag = flag;
	}

	public final Integer getWparam() {
		return wparam;
	}

	public final void setWparam(Integer wparam) {
		this.wparam = wparam;
	}

	public final Integer getLparam() {
		return lparam;
	}

	public final void setLparam(Integer lparam) {
		this.lparam = lparam;
	}

	public final Integer getMachineCode() {
		return machineCode;
	}

	public final void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
	}
	
	
	
}
