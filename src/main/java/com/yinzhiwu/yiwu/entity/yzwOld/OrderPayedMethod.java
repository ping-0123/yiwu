package com.yinzhiwu.yiwu.entity.yzwOld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vorder_payed_method")
public class OrderPayedMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 32)
	private String orderId;

	@Column(length = 32)
	private String payedMethodId;

	@Column
	private float amount;

	@Column
	private Integer sf_create_user;

	@Column
	private Integer sf_last_change_user;

	@Column
	private Date sf_create_time;

	@Column
	private Date sf_last_change_time;

	@Column
	private Integer machineCode;

	@Column
	private Date sf_last_sync_timeStamp;

	@Column
	private Date sf_last_change_timeStamp;

}
