package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vstore_callroll")
public class StoreManCallRollYzw {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 32)
	//TODO 转化为实体
	private String lessonId;

	@Column(length = 32)
	private String memberCard;

	@Column(name="flagCallroll")
	private Boolean callRolled;

	@Column
	private String unCallrollReason;

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
	private Date lastSyncTimeStamp;

	public Integer getId() {
		return id;
	}

	public String getLessonId() {
		return lessonId;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public Boolean getCallRolled() {
		return callRolled;
	}

	public String getUnCallrollReason() {
		return unCallrollReason;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public Integer getLastChangeUserId() {
		return lastChangeUserId;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setCallRolled(Boolean callRolled) {
		this.callRolled = callRolled;
	}

	public void setUnCallrollReason(String unCallrollReason) {
		this.unCallrollReason = unCallrollReason;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
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

	
	
}
