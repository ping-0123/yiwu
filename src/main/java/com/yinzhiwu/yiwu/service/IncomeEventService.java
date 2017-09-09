package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.income.PurchaseEvent;

public interface IncomeEventService extends IBaseService<IncomeEvent, Integer> {

	void saveWithoutSelfIncome(PurchaseEvent event);

}
