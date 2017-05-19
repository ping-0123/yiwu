package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.MessageDao;
import com.yinzhiwu.springmvc3.entity.Message;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Integer> implements MessageDao {

}
