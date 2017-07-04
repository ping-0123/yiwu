package com.yinzhiwu.springmvc3.entity.yzw;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.yinzhiwu.springmvc3.entity.income.CheckInEvent;

@Entity
@Table(name="vcheck_ins")
public class CheckInsYzw extends BaseYzwEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 925280755246130775L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=32)
	private String memberCard;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lessonId", foreignKey=
			@ForeignKey(name="fk_checkIns_lesson_id", value=ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;
	
	@Column(length=32, name="contract")
	private String contractNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teacherId", foreignKey=
			@ForeignKey(name="fk_checkIns_teacher_id", value=ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw teacher;
	
	@Column(length=32)
	private String comsumeSd;
	
	@Column
	private Boolean flag;
	
	@Column
	private String isRetroactive;
	
	@Column
	private Boolean storemanCallroll;
	
	@Column
	private String uncallrollReason;
	
	@OneToOne(mappedBy="checkIn")
	private CheckInEvent event;

	public CheckInsYzw() {
	}
	
	public CheckInsYzw(String memberCard, LessonYzw lesson, String contractNo, EmployeeYzw teacher) {
		super.init();
		this.memberCard = memberCard;
		this.lesson = lesson;
		this.contractNo = contractNo;
		this.teacher = teacher;
	}


	
	@Override
	public void init() {
		super.init();
	}

	
	
	public Integer getId() {
		return id;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public LessonYzw getLesson() {
		return lesson;
	}

	public String getContractNo() {
		return contractNo;
	}

	public EmployeeYzw getTeacher() {
		return teacher;
	}

	public String getComsumeSd() {
		return comsumeSd;
	}

	public Boolean getFlag() {
		return flag;
	}

	public String getIsRetroactive() {
		return isRetroactive;
	}

	public Boolean getStoremanCallroll() {
		return storemanCallroll;
	}

	public String getUncallrollReason() {
		return uncallrollReason;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setLesson(LessonYzw lesson) {
		this.lesson = lesson;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setTeacher(EmployeeYzw teacher) {
		this.teacher = teacher;
	}

	public void setComsumeSd(String comsumeSd) {
		this.comsumeSd = comsumeSd;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setIsRetroactive(String isRetroactive) {
		this.isRetroactive = isRetroactive;
	}

	public void setStoremanCallroll(Boolean storemanCallroll) {
		this.storemanCallroll = storemanCallroll;
	}

	public void setUncallrollReason(String uncallrollReason) {
		this.uncallrollReason = uncallrollReason;
	}

	public CheckInEvent getEvent() {
		return event;
	}

	public void setEvent(CheckInEvent event) {
		this.event = event;
	}




	

	
	
	
}
