package com.yinzhiwu.yiwu.service.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.yinzhiwu.yiwu.dao.MessageDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.event.PayWithdrawEvent;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.MessageApiView;
import com.yinzhiwu.yiwu.service.MessageService;
import com.yinzhiwu.yiwu.util.MessageTemplate;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message, Integer> implements MessageService {

	@Autowired
	private MessageDao messageDao;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();;

	
	
	@Autowired
	private void setBaseDao(MessageDao messageDao) {
		super.setBaseDao(messageDao);
	}
	@Override
	public void saveBrockerageIncomeMessage(Distributer receiver, String customerName, float consumeValue,
			float inComeValue) {
		if (receiver == null)
			return;
		Message message = new Message();
		String content = "您的客户：" + customerName + "在音之舞消费了" + consumeValue + "元， " + "您获得了" + inComeValue + "收益";
		message.setContent(content);
		message.setReceiver(receiver);
		messageDao.save(message);
	}

	@Override
	public void saveSubordinateRegisterMessage(Distributer receiver, String customerName, float consumeValue,
			float inComeValue) {
		Message message = new Message();
		String content = customerName + "通过您的邀请码: " + receiver.getShareCode() + "注册成为音之舞的会员," + "您获得了" + inComeValue
				+ "收益";
		message.setContent(content);
		message.setReceiver(receiver);
		messageDao.save(message);
	}

	@Override
	public YiwuJson<List<MessageApiView>> findByReceiverId(int receiverId) {
		List<Message> messages = messageDao.findByReceiverId(receiverId);
		if (messages.size() == 0)
			return new YiwuJson<>("this receiver: " + receiverId + " has no messages ");
		List<MessageApiView> views = new ArrayList<>();
		for (Message m : messages) {
			views.add(new MessageApiView(m));
		}
		return new YiwuJson<>(views);
	}

	@Override
	public YiwuJson<MessageApiView> findById(int id) {
		try {
			Message message = messageDao.get(id);
			if (message == null)
				return new YiwuJson<>("no message found by id " + id);

			// 更改消息状态
			message.setStatus(Message.Status.READ);
			messageDao.update(message);

			// 返回
			return new YiwuJson<>(new MessageApiView(message));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public void saveWithdrawMessage(Distributer receiver, float value) {
		if (receiver == null)
			return;
		Message message = new Message();
		String content = "您于" + format.format(new Date()) + "提现" + value + "元";
		message.setContent(content);
		message.setReceiver(receiver);
		messageDao.save(message);
	}

	
	//TODO 好好研究spel
	@TransactionalEventListener(classes={IncomeRecord.class}
//		, condition="#{record.incomeType == BROKERAGE}"
	)
	public void handleBrokerageRecord(IncomeRecord record){
		if(IncomeType.BROKERAGE == record.getIncomeType()){
			Message message = new Message(record.getBenificiary(),
					MessageTemplate.generate_brokerage_income_message(record));
			save(message);
		}
	}

	//TODO test @TransactionalEventListener  or reference http://www.jdon.com/dl/best/springevent.html
	@TransactionalEventListener(classes={PayWithdrawEvent.class})
	public void handlePayWithdrawEvent(PayWithdrawEvent event){
		WithdrawBrokerage withdraw = (WithdrawBrokerage) event.getSource();
		String msg = "您于" + format.format(withdraw.getCreateTime())
				+ "从音之舞E5系统账户中提现RMB " + currencyFormat.format( withdraw.getAmount())
				+ "元，已经打款，请注意查收";
		
		Message message = new Message();
		message.setReceiver(withdraw.getDistributer());
		message.setContent(msg);
		
		super.save(message);
	}
}
