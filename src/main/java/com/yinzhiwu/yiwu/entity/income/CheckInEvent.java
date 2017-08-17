package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;

@Entity
@PrimaryKeyJoinColumn(name="id")
public abstract class CheckInEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137944108496433914L;

	@OneToOne(fetch=FetchType.LAZY)
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
