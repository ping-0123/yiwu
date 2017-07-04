package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.util.MessageTemplate;

@Entity
@DiscriminatorValue("DepositEvent")
public class DepositEvent extends IncomeEvent{
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5121826589475801050L;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(foreignKey = 
		@ForeignKey(name="fk_depositEvent_order_id", value =ConstraintMode.NO_CONSTRAINT))
	private OrderYzw order;
	
	
	
	public DepositEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public DepositEvent(Distributer distributer, EventType type, Float param ,OrderYzw order) {
		super(distributer, type, param);
		this.order = order;
	}

	
	public DepositEvent(){}

	@Override
	public Message generateMessage(IncomeRecord record) {
		try{
			return new Message(record.getBenificiary(),
				MessageTemplate.BrokerageMessage.generate_pay_deposit_message(this.getOccurTime(), this.getParam()));
		}catch (Exception e) {
			return null;
		}
	}

	public OrderYzw getOrder() {
		return order;
	}

	public void setOrder(OrderYzw order) {
		this.order = order;
	}



	
}
