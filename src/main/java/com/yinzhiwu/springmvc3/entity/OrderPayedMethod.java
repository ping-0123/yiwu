package com.yinzhiwu.springmvc3.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vorder_payed_method")
public class OrderPayedMethod {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=32)
	private String orderId;
	
	@Column(length=32)
	private  String payedMethodId;
	
	@Column
	private float amount;
	
	@Column
	private Integer createUserId;
	
	@Column
	private Integer lastChangeUserId;
	
	@Column
	private Date createTime;
	
	@Column
	private Date lastChangeTime;
	
	@Column
	private Integer machineCode;
	
	@Column 
	private Date lastSyncTimestamp;
	
	@Column
	private Date lastChangeTimestamp;
	
	
}
