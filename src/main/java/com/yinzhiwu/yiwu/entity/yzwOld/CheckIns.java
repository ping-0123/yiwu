package com.yinzhiwu.yiwu.entity.yzwOld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vcheck_ins")
public class CheckIns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 32)
	private String memberCard;

	@Column(length = 32, name="lesson_id")
	private String lessonId;

	@Column(length = 32, name="contractNo")
	private String contract;

	@Column(name="teacher_id")
	private Integer teacherId;

	@Column(length = 32)
	private String comsumeSd;

	@Column
	private Short flag;

	@Column
	private String isRetroactive;

	@Column
	private Short storemanCallroll;

	@Column
	private String uncallrollReason;

	@Column(name = "sf_create_user")
	private Integer createUserId;

	@Column(name = "sf_last_change_user")
	private Integer lastChangeUserId;

	@Column(name = "sf_create_time")
	private Date createTime;

	@Column(name = "sf_last_change_time")
	private Date lastChangeTime;

	@Column
	private Integer machineCode;

	@Column(name = "sf_last_sync_timeStamp")
	private Date lastSyncTimeStamp;

	@Column(name = "sf_last_change_timeStamp")
	private Date lastChangeTimeStamp;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getMemberCard() {
		return memberCard;
	}

	public final void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public final String getLessonId() {
		return lessonId;
	}

	public final void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public final String getContract() {
		return contract;
	}

	public final void setContract(String contract) {
		this.contract = contract;
	}

	public final Integer getTeacherId() {
		return teacherId;
	}

	public final void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public final String getComsumeSd() {
		return comsumeSd;
	}

	public final void setComsumeSd(String comsumeSd) {
		this.comsumeSd = comsumeSd;
	}

	public final Short getFlag() {
		return flag;
	}

	public final void setFlag(Short flag) {
		this.flag = flag;
	}

	public final String getIsRetroactive() {
		return isRetroactive;
	}

	public final void setIsRetroactive(String isRetroactive) {
		this.isRetroactive = isRetroactive;
	}

	public final Short getStoremanCallroll() {
		return storemanCallroll;
	}

	public final void setStoremanCallroll(Short storemanCallroll) {
		this.storemanCallroll = storemanCallroll;
	}

	public final String getUncallrollReason() {
		return uncallrollReason;
	}

	public final void setUncallrollReason(String uncallrollReason) {
		this.uncallrollReason = uncallrollReason;
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

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
	}

}
