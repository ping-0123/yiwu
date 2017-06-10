package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.EventType;

@Entity
@DiscriminatorValue("RegisterEvent")
public class RegisterEvent extends IncomeEvent {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3330880531568104632L;
	
	@Column(length = 32)
	private String invitaionCode;
	
	public RegisterEvent(){}

	public RegisterEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public RegisterEvent(Distributer distributer, EventType type, Float param, String invitaionCode) {
		super(distributer, type, param);
		this.invitaionCode = invitaionCode;
	}
	
	
	public String getInvitaionCode() {
		return invitaionCode;
	}

	public void setInvitaionCode(String invitaionCode) {
		this.invitaionCode = invitaionCode;
	}




	
}
