package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DepositDao;
import com.yinzhiwu.springmvc3.entity.income.DepositEvent;

@Repository
public class DepositDaoImpl extends BaseDaoImpl<DepositEvent,Integer> implements DepositDao {

}
