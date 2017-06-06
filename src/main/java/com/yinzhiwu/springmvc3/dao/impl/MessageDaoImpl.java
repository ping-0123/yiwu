package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.MessageDao;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Integer> implements MessageDao {

	@Override
	public List<Message> findByReceiverId(int receiverId) {
		try {
			return findByProperty("receiver.id", receiverId);
		} catch (DataNotFoundException e) {
			return new ArrayList<>();
		}
	}

}
