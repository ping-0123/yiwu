package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

@MapedClass(LessonInteractive.class)
public class LessonInteractiveVO {
	
	@MapedProperty(value="lesson.id")
	private Integer lessonId;
	
	@MapedProperty(value="distributer.id")
	private Integer distributerId;
	
	private String contracNo;
	
	private  Boolean appointed;
	
	private  Boolean checkedIn;
	
	private Boolean storemanCalled;
	
	private Boolean coachCalled;
	
	private  Boolean praised;
	
	private  Boolean firstCommented;
	
	private  Boolean appendCommented;
	
	private  Boolean workFinished;

	public static final class LessonInteractiveVOConverter extends AbstractConverter<LessonInteractive, LessonInteractiveVO>{
		public static final LessonInteractiveVOConverter INSTANCE = new LessonInteractiveVOConverter();
	}
	
	/**
	 * @return the lessonId
	 */
	public Integer getLessonId() {
		return lessonId;
	}

	/**
	 * @param lessonId the lessonId to set
	 */
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	/**
	 * @return the distributerId
	 */
	public Integer getDistributerId() {
		return distributerId;
	}

	/**
	 * @param distributerId the distributerId to set
	 */
	public void setDistributerId(Integer distributerId) {
		this.distributerId = distributerId;
	}

	/**
	 * @return the contracNo
	 */
	public String getContracNo() {
		return contracNo;
	}

	/**
	 * @param contracNo the contracNo to set
	 */
	public void setContracNo(String contracNo) {
		this.contracNo = contracNo;
	}

	/**
	 * @return the appointed
	 */
	public Boolean getAppointed() {
		return appointed;
	}

	/**
	 * @param appointed the appointed to set
	 */
	public void setAppointed(Boolean appointed) {
		this.appointed = appointed;
	}

	/**
	 * @return the checkedIn
	 */
	public Boolean getCheckedIn() {
		return checkedIn;
	}

	/**
	 * @param checkedIn the checkedIn to set
	 */
	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/**
	 * @return the storemanCalled
	 */
	public Boolean getStoremanCalled() {
		return storemanCalled;
	}

	/**
	 * @param storemanCalled the storemanCalled to set
	 */
	public void setStoremanCalled(Boolean storemanCalled) {
		this.storemanCalled = storemanCalled;
	}

	/**
	 * @return the coachCalled
	 */
	public Boolean getCoachCalled() {
		return coachCalled;
	}

	/**
	 * @param coachCalled the coachCalled to set
	 */
	public void setCoachCalled(Boolean coachCalled) {
		this.coachCalled = coachCalled;
	}

	/**
	 * @return the praised
	 */
	public Boolean getPraised() {
		return praised;
	}

	/**
	 * @param praised the praised to set
	 */
	public void setPraised(Boolean praised) {
		this.praised = praised;
	}

	/**
	 * @return the firstCommented
	 */
	public Boolean getFirstCommented() {
		return firstCommented;
	}

	/**
	 * @param firstCommented the firstCommented to set
	 */
	public void setFirstCommented(Boolean firstCommented) {
		this.firstCommented = firstCommented;
	}

	/**
	 * @return the appendCommented
	 */
	public Boolean getAppendCommented() {
		return appendCommented;
	}

	/**
	 * @param appendCommented the appendCommented to set
	 */
	public void setAppendCommented(Boolean appendCommented) {
		this.appendCommented = appendCommented;
	}

	/**
	 * @return the workFinished
	 */
	public Boolean getWorkFinished() {
		return workFinished;
	}

	/**
	 * @param workFinished the workFinished to set
	 */
	public void setWorkFinished(Boolean workFinished) {
		this.workFinished = workFinished;
	}
	
	
}
