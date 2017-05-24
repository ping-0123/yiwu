package com.yinzhiwu.springmvc3.service.impl;

import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.CapitalAccountTypeDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.CapitalAccountType;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.service.CapitalAccountService;
import com.yinzhiwu.springmvc3.util.CapitalAccountTypeUtil;

@Service
public class CapitalAccountServiceImpl extends BaseServiceImpl<CapitalAccount, Integer> implements CapitalAccountService{
	
	@Autowired
	private CapitalAccountDao caDao;
	
	@Autowired
	private CapitalAccountTypeDao catDao;
	
	@Autowired
	private DistributerDao dDao;
	
	@Autowired
	public  void setBaseDao(CapitalAccountDao capitalAccountDao){
		super.setBaseDao(capitalAccountDao);
	}

	@Override
	public YiwuJson<CapitalAccountApiView> addCapitalAccount(CapitalAccountApiView v) {
		CapitalAccountType type = catDao.get(CapitalAccountTypeUtil.toTypeId(v.getTypeName()));
		Distributer d = dDao.get(v.getDistributerId());
		CapitalAccount account = new CapitalAccount();
		account.setCapitalAccountType(type);
		account.setDistributer(d);
		account.setAccount(v.getAccount());
		caDao.save(account);
		
		return new YiwuJson<CapitalAccountApiView>(
				new CapitalAccountApiView(account));
	}

}
