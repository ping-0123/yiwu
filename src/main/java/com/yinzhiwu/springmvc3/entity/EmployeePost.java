package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vemployee_post")
public class EmployeePost {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private int employeeId;
	
	@Column(name="postID")
	private int postId;
	
	@Column
	private int lastChangerId;
	
	@Column
	private Date lastChangeTime;
	
	@Column
	private int removed;
	
	@Column
	private int machineCode;
	
	@Column
	private Date lastSyncTimestamp;
	
	@Column
	private Date lastChangeTimestamp;
	
}
