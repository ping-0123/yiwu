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
import javax.persistence.Table;

@Entity
@Table(name="vcheck_ins")
public class CheckInsYzw {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=32)
	private String memberCard;
	
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
	private Short flag;
	
	@Column
	private String isRetroactive;
	
	@Column
	private Short storemanCallroll;
	
	@Column
	private String uncallrollReason;
	
	
	
	
}
