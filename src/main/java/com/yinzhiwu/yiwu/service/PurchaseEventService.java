package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.PurchaseEvent;

public interface PurchaseEventService extends IBaseService<PurchaseEvent, Integer>{

	void saveAllLastDayPurchaseEvents();

}
