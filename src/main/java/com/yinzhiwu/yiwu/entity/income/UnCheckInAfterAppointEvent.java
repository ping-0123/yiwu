package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.EventType;

/**
*@Author ping
*@Time  创建时间:2017年8月12日下午4:10:46
*
*/

@Entity
@DiscriminatorValue("UnCheckInAfterAppointEvent")
public class UnCheckInAfterAppointEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7564770446991546370L;

	public UnCheckInAfterAppointEvent() {
	}

	public UnCheckInAfterAppointEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

}
