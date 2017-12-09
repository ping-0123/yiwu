
package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.CourseType.CourseTypeConverter;

/**
 * @author ping
 * @date 2017年12月9日上午10:58:17
 * @since v2.2.0
 *	
 */

@Entity
@Table(name="vcoach_callroll")
public class CoachCallRoll extends BaseYzwEntity{

	private static final long serialVersionUID = -8931695579408902854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=32)
	private String memberCard;
	
	@Convert(converter=CourseTypeConverter.class)
	private CourseType courseType;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;
	
	private Boolean isCalled;
	
	@Column(length=32)
	private String studentName;
	
	private Integer slotCardId;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer;
	
	@Column(length = 128)
	private String unCalledRollReason;
	
	private Boolean isFilledUp;
	
	

	@Override
	public void init() {
		super.init();
		if(null == isCalled) isCalled =true;
		if(null == isFilledUp) isFilledUp=false;
	}

	public Integer getId() {
		return id;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public LessonYzw getLesson() {
		return lesson;
	}

	public Boolean getIsCalled() {
		return isCalled;
	}

	public String getStudentName() {
		return studentName;
	}

	public Integer getSlotCardId() {
		return slotCardId;
	}

	public CustomerYzw getCustomer() {
		return customer;
	}

	public String getUnCalledRollReason() {
		return unCalledRollReason;
	}

	public Boolean getIsFilledUp() {
		return isFilledUp;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setIsCalled(Boolean isCalled) {
		this.isCalled = isCalled;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setSlotCardId(Integer slotCardId) {
		this.slotCardId = slotCardId;
	}

	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
	}

	public void setUnCalledRollReason(String unCalledRollReason) {
		this.unCalledRollReason = unCalledRollReason;
	}

	public void setIsFilledUp(Boolean isFilledUp) {
		this.isFilledUp = isFilledUp;
	}
	
	
	
}
