package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.yinzhiwu.yiwu.entity.Distributer;

@Entity
@Table(name = "vcheck_ins")
@Where(clause="settleStatus <> 'NO_SETTLE'")
public class LessonCheckInYzw extends BaseYzwEntity {

	private static final long serialVersionUID = 925280755246130775L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private Distributer distributer;

	@Column(length = 32)
	private String memberCard;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_id", foreignKey = @ForeignKey(name = "fk_checkIns_lesson_id", value = ConstraintMode.NO_CONSTRAINT))
	private LessonYzw lesson;

	@Column(length = 32, name = "contractNo")
	private String contractNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "fk_checkIns_teacher_id", value = ConstraintMode.NO_CONSTRAINT))
	private EmployeeYzw teacher;

	@Column(length = 32)
	private String comsumeSd;

	@Column
	private Boolean flag;

	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private SettleStatus settleStatus;
	
	@Column
	private String isRetroactive;

	@Column
	private Boolean storemanCallroll;

	@Column
	private String uncallrollReason;
	
	private Boolean appointed;

//	@OneToOne(mappedBy = "checkIn", fetch=FetchType.LAZY)
//	private CheckInEvent event;

	public LessonCheckInYzw() {
	}

	public LessonCheckInYzw(String memberCard, LessonYzw lesson, String contractNo, EmployeeYzw teacher) {
		super.init();
		this.memberCard = memberCard;
		this.lesson = lesson;
		this.contractNo = contractNo;
		this.teacher = teacher;
	}

	public LessonCheckInYzw(Distributer distributer, LessonYzw lesson, String contractNo){
		this.distributer = distributer;
		this.memberCard = distributer.getMemberCard();
		this.lesson = lesson;
		this.contractNo = contractNo;
	}
	@Override
	public void init() {
		super.init();
		if(null == appointed)
			appointed=false;
		if(null == storemanCallroll)
			storemanCallroll=false;
		if(null == flag)
			flag=false;
		if(null == settleStatus)
			settleStatus = SettleStatus.UN_SETTLED;
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

	public Distributer getDistributer() {
		return distributer;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
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

	
	
	public SettleStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
	}




	public  enum SettleStatus{
		UN_SETTLED, /** 未结算 **/
		NO_SETTLE,	/** 不结算  比如教师重复刷卡， 其中一条是不结算的**/
		SETTLED 	/** 已结算 **/
	}

}
