package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.util.MessageTemplate;

@Entity
@DiscriminatorValue("DepositEvent")
public class DepositEvent extends IncomeEvent{

	public DepositEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public DepositEvent(){}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5121826589475801050L;

	@Override
	public Message generateMessage(IncomeRecord record) {
		try{
			return new Message(record.getBenificiary(),
				MessageTemplate.BrokerageMessage.generate_pay_deposit_message(this.getOccurTime(), this.getParam()));
		}catch (Exception e) {
			return null;
		}
	}

	
}
