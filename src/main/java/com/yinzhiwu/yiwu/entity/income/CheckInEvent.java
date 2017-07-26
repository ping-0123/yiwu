package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;

@Entity
@DiscriminatorValue("CheckInEvent")
public abstract class CheckInEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137944108496433914L;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private CheckInsYzw checkIn;

	public CheckInsYzw getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckInsYzw checkIn) {
		this.checkIn = checkIn;
	}

	public CheckInEvent() {
	}

	public CheckInEvent(Distributer distributer, EventType type, Float param, CheckInsYzw checkIn) {
		super(distributer, type, param);
		this.checkIn = checkIn;
	}

	public CheckInEvent(Distributer distributer, Float param, CheckInsYzw checkIn) {
		super(distributer, null, param);
		this.checkIn = checkIn;
	}
}
