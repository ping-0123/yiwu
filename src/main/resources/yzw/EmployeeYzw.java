package com.yinzhiwu.springmvc3.entity.yzw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity
//@Table(name="vemployee")
public class EmployeeYzw extends BaseYzwEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6739017966853748277L;
	
	@Id
	@Column(length=32)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=128)
	private String user;
	
	@Column(length=128)
	private String seegleUserId;
	
	@Column(length=128)
	private String name;
	
	@Column(length=128)
	private String password;
	
	@Column(name="Sex")
	private Integer gender;
	
	@Column(length=20)
	private String birthday;
	
	@Column(length=20)
	private String tel;
	
	@Column(length=20)
	private String cellPhone;
	
	@Column(length=20)
	private String fax;
	
	@Column(length=50)
	private String email;
	
	@Column
	private Integer disabled;
	
	@Column
	private Integer creator;
	
	@Column
	private Integer lastChanger;
	
	@Column
	private Date createTime;
	
	@Column
	private Integer removed;
	
	@Column
	private Integer wparam;
	
	@Column
	private Integer lparam;
	
	@Column
	private Integer machineCode;
	
	@Column(length=128)
	private String seegleUserName;
	
	@Column(length=128)
	private String seegleSerialNum;
	
	@Column
	private Integer userType;
	
	@Column(length=128)
	private String accessCode;
	
	@Column(length=128)
	private String bindMac;
	
	@Column(name="Sf_Last_Sync_TimeStamp")
	private Date lastSyncTimeStamp;
	
	@Column(name="Sf_Last_Change_Timestamp")
	private Date lastChangeTimeStamp;
	
	@Column
	private String fingerPrintNo;
	
	@Column
	private Integer departmentId;
	
	@Column
	private Integer defaultDuty;
	
	@Column
	private Integer outUser;
	
	@Column(length=32)
	private String clusterServerIp;
	
	@Column
	private Integer clusterServerPort;
	
	@Column(length=32)
	private String clusterToken;
	
	@Column(name="Last_Online_TimeStamp")
	private Date lastOnlineTimeStamp;
	
	
	

}
