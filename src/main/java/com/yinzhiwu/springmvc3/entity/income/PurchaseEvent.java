package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.util.MessageTemplate;

@Entity
@DiscriminatorValue("PurchaseEvent")
public class PurchaseEvent extends IncomeEvent {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2637640553460290688L;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_incomeEventPurchase_order_id", value=ConstraintMode.NO_CONSTRAINT))
	private OrderYzw order;
	
	public PurchaseEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}
	
	public PurchaseEvent() {}

	public PurchaseEvent(Distributer distributer, EventType type, Float param,OrderYzw order) {
		super(distributer, type, param);
		this.order = order;
	}
	
	@Override
	public Message generateMessage(IncomeRecord record) {
		try{
			return new Message(record.getBenificiary(),MessageTemplate.BrokerageMessage.generate_purchase_products_message(
					getDistributer().getName(), getOccurTime(), getParam(), record.getIncomeValue()));
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
