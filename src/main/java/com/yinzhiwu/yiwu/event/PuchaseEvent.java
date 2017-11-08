package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月9日 上午1:28:27
*
*/

@SuppressWarnings("serial")
public class PuchaseEvent extends ApplicationEvent implements IncomeEvent {

	public PuchaseEvent(OrderYzw order) {
		super(order);
	}

	@Override
	public IncomeEventType getType() {
		return IncomeEventType.PURCHASE_PRODUCTS;
	}

	@Override
	public String getSourceId() {
		return ((OrderYzw) getSource()).getId();
	}
	
}
