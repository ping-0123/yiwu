package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;

public interface MessageService  extends IBaseService<Message, Integer>{

	public void saveBrockerageIncomeMessage(Distributer receiver, String customerName, float consumeValue, float inComeValue);
}
