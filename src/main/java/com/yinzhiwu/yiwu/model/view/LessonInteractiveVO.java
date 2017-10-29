package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.LessonInteractive;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午1:44:29
 *
 */

@MapedClass(LessonInteractive.class)
@ApiModel("描述用户与课时交互结果")
public class LessonInteractiveVO {
	
	@MapedProperty(value="lesson.id")
	@ApiModelProperty("课时Id")
	private Integer lessonId;
	
	@MapedProperty(value="distributer.id")
	@ApiModelProperty("用户Id")
	private Integer distributerId;
	
	@ApiModelProperty("交互所使用的会籍合约编号")
	private String contracNo;
	
	@ApiModelProperty("是否已预约")
	private  Boolean appointed;
	
	@ApiModelProperty("是否已签到")
	private  Boolean checkedIn;
	
	@ApiModelProperty("是否已被店员点名")
	private Boolean storemanCalled;
	
	@ApiModelProperty("是否已被教练点名")
	private Boolean coachCalled;
	
	@ApiModelProperty("是否已点赞")
	private  Boolean praised;
	
	@ApiModelProperty("是否已评论")
	private  Boolean firstCommented;
	
	@ApiModelProperty("是否已追评")
	private  Boolean appendCommented;
	
	@ApiModelProperty("是否已完成老师布置的作业")
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
