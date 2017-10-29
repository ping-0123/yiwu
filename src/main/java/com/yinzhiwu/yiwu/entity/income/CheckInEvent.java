package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.LessonCheckInYzw;

@Entity
@DiscriminatorValue("CheckInEvent")
public abstract class CheckInEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137944108496433914L;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private LessonCheckInYzw checkIn;

	public LessonCheckInYzw getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LessonCheckInYzw checkIn) {
		this.checkIn = checkIn;
	}

	public CheckInEvent() {
	}

	public CheckInEvent(Distributer distributer, EventType type, Float param, LessonCheckInYzw checkIn) {
		super(distributer, type, param);
		this.checkIn = checkIn;
	}

	public CheckInEvent(Distributer distributer, Float param, LessonCheckInYzw checkIn) {
		super(distributer, null, param);
		this.checkIn = checkIn;
	}
	
	public CheckInEvent(LessonCheckInYzw checkIn) {
		super(checkIn.getDistributer(), null, 1f);
		this.checkIn = checkIn;
		if(checkIn.getAppointed())
			this.setType(EventType.CHECK_IN_AFTER_APPOINTMENT);
		else
			this.setType(EventType.CHECK_IN_WITHOUT_APPOINTMENT);
	}
}
