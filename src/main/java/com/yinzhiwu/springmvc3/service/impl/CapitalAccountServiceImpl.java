package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.service.CapitalAccountService;

@Service
public class CapitalAccountServiceImpl extends BaseServiceImpl<CapitalAccount, Integer> implements CapitalAccountService{
	
	@Autowired
	public  void setBaseDao(CapitalAccountDao capitalAccountDao){
		super.setBaseDao(capitalAccountDao);
	}
	

}
