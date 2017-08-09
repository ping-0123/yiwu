package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.service.CapitalAccountService;

@Service
public class CapitalAccountServiceImpl extends BaseServiceImpl<CapitalAccount, Integer>
		implements CapitalAccountService {

	@Autowired
	public void setBaseDao(CapitalAccountDao capitalAccountDao) {
		super.setBaseDao(capitalAccountDao);
	}

}
