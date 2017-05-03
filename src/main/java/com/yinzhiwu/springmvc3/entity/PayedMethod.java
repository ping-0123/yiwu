package com.yinzhiwu.springmvc3.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vpayed_method")
public class PayedMethod {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=32)
	private String name;
	
	@Column
	private int createUserId;
	
	@Column
	private int lastChangeUserId;
	
	@Column
	private Date createTime;
	
	@Column
	private Date lastChangeTime;
	
	@Column
	private int machineCode;
	
	@Column
	private Date lastSyncTimestamp;
	
	@Column
	private Date lastChangeTimestamp;
	
}
