package com.yinzhiwu.springmvc3.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vinterval")
public class Interval {
	
	@Id
	@GeneratedValue
	@Column(length=32)
	private String id;
	
	@Column(length=32)
	private String name;
	
	@Column
	private Time start;
	
	@Column
	private Time end;
	
	@Column
	private float hours;

	private int sf_create_user;
	
	private int sf_last_change_user;
	
	private Date sf_create_time;
	
	private Date sf_last_change_time;
	
	private int machineCode;
	
	private Date sf_last_sync_timeStamp;
	
	private Date  sf_last_change_timeStamp;
	
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

	public final Time getStart() {
		return start;
	}

	public final void setStart(Time start) {
		this.start = start;
	}

	public final Time getEnd() {
		return end;
	}

	public final void setEnd(Time end) {
		this.end = end;
	}

	public final float getHours() {
		return hours;
	}

	public final void setHours(float hours) {
		this.hours = hours;
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


	public void setSf_last_change_timeStamp(Date sf_last_change_timeStamp) {
		this.sf_last_change_timeStamp = sf_last_change_timeStamp;
	}

	public Date getSf_last_sync_timeStamp() {
		return sf_last_sync_timeStamp;
	}

	public void setSf_last_sync_timeStamp(Date sf_last_sync_timeStamp) {
		this.sf_last_sync_timeStamp = sf_last_sync_timeStamp;
	}
	
	
}
