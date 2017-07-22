package com.yinzhiwu.yiwu.entity.yzwOld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vstore_callroll")
public class StoreManCallRoll {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=32)
	private String lessonId;
	
	@Column(length=32)
	private String memberCard;
	
	@Column
	private Integer flagCallroll;
	
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

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getLessonId() {
		return lessonId;
	}

	public final void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public final String getMemberCard() {
		return memberCard;
	}

	public final void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public final Integer getFlagCallroll() {
		return flagCallroll;
	}

	public final void setFlagCallroll(Integer flagCallroll) {
		this.flagCallroll = flagCallroll;
	}

	public final String getUnCallrollReason() {
		return unCallrollReason;
	}

	public final void setUnCallrollReason(String unCallrollReason) {
		this.unCallrollReason = unCallrollReason;
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
	
	
	

		
}
