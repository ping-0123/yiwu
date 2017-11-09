package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.MessageApiView;

public interface MessageService extends IBaseService<Message, Integer> {

	public void saveBrockerageIncomeMessage(Distributer receiver, String customerName, float consumeValue,
			float inComeValue);

	public YiwuJson<List<MessageApiView>> findByReceiverId(int receiverId);

	public YiwuJson<MessageApiView> findById(int id);

	void saveSubordinateRegisterMessage(Distributer receiver, String customerName, float consumeValue,
			float inComeValue);

	public void saveWithdrawMessage(Distributer beneficiary, float value);

}
