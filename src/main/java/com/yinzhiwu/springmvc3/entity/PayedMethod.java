package com.yinzhiwu.springmvc3.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vpayed_method")
public class PayedMethod {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=32)
	private String name;
	
	@Column
	private int sf_create_user;
	
	@Column
	private int sf_last_change_user;
	
	@Column
	private Date sf_create_time;
	
	@Column
	private Date sf_last_change_time;
	
	@Column
	private int machineCode;
	
	@Column
	private Date sf_last_sync_timeStamp;
	
	@Column
	private Date sf_last_change_timeStamp;
	
}
