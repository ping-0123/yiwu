package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.Message;

public interface MessageDao extends IBaseDao<Message, Integer> {

	List<Message> findByReceiverId(int receiverId);

}
