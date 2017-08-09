package com.yinzhiwu.yiwu.entity.yzwOld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vteacher_callroll")
public class TeacherCallRoll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 32)
	private String memberCard;

	@Column(length = 32)
	private String courseType;

	@Column
	private Integer lessonId;

	@Column
	private Integer callroll;

	@Column(length = 32)
	private String studentName;

	@Column
	private Integer slotCardId;

	@Column
	private Integer memberId;

	@Column
	private String unCallRollReason;

	@Column
	private Integer isFilledUp;

	@Column(name = "createUserid")
	private Integer createUserId;

	@Column
	private Integer lastChangeUserId;

	@Column
	private Date createTime;

	@Column
	private Date lastChangeTime;

	@Column
	private Date lastChangeTimeStamp;

	@Column
	private Integer machineCode;

	@Column
	Date lastSyncTimeStamp;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getMemberCard() {
		return memberCard;
	}

	public final void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public final String getCourseType() {
		return courseType;
	}

	public final void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public final Integer getLessonId() {
		return lessonId;
	}

	public final void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public final Integer getCallroll() {
		return callroll;
	}

	public final void setCallroll(Integer callroll) {
		this.callroll = callroll;
	}

	public final String getStudentName() {
		return studentName;
	}

	public final void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public final Integer getSlotCardId() {
		return slotCardId;
	}

	public final void setSlotCardId(Integer slotCardId) {
		this.slotCardId = slotCardId;
	}

	public final Integer getMemberId() {
		return memberId;
	}

	public final void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public final String getUnCallRollReason() {
		return unCallRollReason;
	}

	public final void setUnCallRollReason(String unCallRollReason) {
		this.unCallRollReason = unCallRollReason;
	}

	public final Integer getIsFilledUp() {
		return isFilledUp;
	}

	public final void setIsFilledUp(Integer isFilledUp) {
		this.isFilledUp = isFilledUp;
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

	public final Date getLastChangeTimeStamp() {
		return lastChangeTimeStamp;
	}

	public final void setLastChangeTimeStamp(Date lastChangeTimeStamp) {
		this.lastChangeTimeStamp = lastChangeTimeStamp;
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
