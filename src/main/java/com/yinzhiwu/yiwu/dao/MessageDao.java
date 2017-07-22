package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Message;

public interface MessageDao extends IBaseDao<Message, Integer> {

	List<Message> findByReceiverId(int receiverId);

}
