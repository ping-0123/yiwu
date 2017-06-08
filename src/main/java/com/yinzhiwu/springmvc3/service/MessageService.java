package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.IncomeRecord;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.MessageApiView;

public interface MessageService  extends IBaseService<Message, Integer>{

	public void saveBrockerageIncomeMessage(Distributer receiver, String customerName, float consumeValue, float inComeValue);

	public YiwuJson<List<MessageApiView>> findByReceiverId(int receiverId);

	public YiwuJson<MessageApiView> findById(int id);

	void saveSubordinateRegisterMessage(Distributer receiver, String customerName, float consumeValue,
			float inComeValue);

	public void saveWithdrawMessage(Distributer beneficiary, float value);

	public void save_by_record(IncomeRecord incomeRecord);
}
