package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.PurchaseEvent;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;

public interface PurchaseEventService extends IBaseService<PurchaseEvent, Integer> {

	void saveAllLastDayPurchaseEvents();

	void savePurchaseEvent(OrderYzw order);

	void savePurchaseEventWithoutSelfIncome(OrderYzw order);

}
