package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.MessageDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.service.MessageService;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message, Integer> implements MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private void setBaseDao(MessageDao messageDao)
	{
		super.setBaseDao(messageDao);
	}

	@Override
	public void saveBrockerageIncomeMessage(Distributer receiver, String customerName, float consumeValue,
			float inComeValue) {
		if(receiver == null)
			return;
		Message message = new Message();
		String content = "您的客户：" + customerName + "在音之舞消费了" + consumeValue + "元， "
				+ "您获得了" + inComeValue + "收益";
		message.setContent(content);
		message.setReceiver(receiver);
		messageDao.save(message);
	}
}
