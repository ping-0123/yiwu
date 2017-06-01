package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.MessageDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.MessageApiView;
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

	@Override
	public YiwuJson<List<MessageApiView>> findByReceiverId(int receiverId) {
		List<Message> messages = messageDao.findByReceiverId(receiverId);
		if(messages.size() ==0)
			return new YiwuJson<>("this receiver: " + receiverId + " has no messages ");
		List<MessageApiView> views = new ArrayList<>();
		for (Message m : messages) {
			views.add(new MessageApiView(m));
		}
		return new YiwuJson<>(views);
	}

	@Override
	public YiwuJson<MessageApiView> findById(int id) {
		try{
			Message  message = messageDao.get(id);
			if(message == null)
				return new YiwuJson<>("no message found by id " + id);
			
			//更改消息状态
			message.setStatus(Message.Status.READ);
			messageDao.update(message);
			
			//返回
			return new YiwuJson<>(new MessageApiView(message));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
}
