package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.util.MessageTemplate;

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
	
	/**
	 * 收益参数是否应该排除定金？（定金有两种来源， 一种是顾客实付现金， 另外一种是分销系统的余额支付), 在本构造方法中没有排除定金
	 * @param distributer
	 * @param order
	 */
	public PurchaseEvent(Distributer distributer,  OrderYzw order){
		super(distributer, EventType.PURCHASE_PRODUCTS, order.getPayedAmount());
		this.order  = order;
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
