package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.MessageDao;
import com.yinzhiwu.yiwu.entity.Message;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Integer> implements MessageDao {

	@Override
	public List<Message> findByReceiverId(int receiverId) {
			return findByProperty("receiver.id", receiverId);
	}

}
