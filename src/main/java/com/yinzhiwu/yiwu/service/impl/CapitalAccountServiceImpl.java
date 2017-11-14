package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.service.CapitalAccountService;

@Service
public class CapitalAccountServiceImpl extends BaseServiceImpl<CapitalAccount, Integer>
		implements CapitalAccountService {

	@Autowired
	public void setBaseDao(CapitalAccountDao capitalAccountDao) {
		super.setBaseDao(capitalAccountDao);
	}

	@Autowired private CapitalAccountDao capitalAccountDao;
	
	@Override
	public List<CapitalAccount> findByDistributerId(int distributerId) {
		return capitalAccountDao.findByDistributerId(distributerId);
	}

	@Override
	public List<CapitalAccount> findByDistributerAndPaymentMode(Distributer distributer, PaymentMode paymentMode) {
		return capitalAccountDao.findByDistributerIdAndPaymentMode(distributer.getId(),paymentMode);
	}

	@Override
	public CapitalAccount findOneByDistributerAndPaymentMode(Distributer distributer, PaymentMode paymentMode) {
		return capitalAccountDao.findOneByDistributerIdAndPaymentMode(distributer.getId(),paymentMode);
	}
	
	@Override
	public Integer save(CapitalAccount account) {
		if(null != account.getIsDefault() && account.getIsDefault())
			capitalAccountDao.cancelDefaultAccount(account.getDistributer().getId());
		return super.save(account);
	}

	@Override
	public void update(CapitalAccount account) {
		if(null != account.getIsDefault() && account.getIsDefault())
			capitalAccountDao.cancelDefaultAccount(account.getDistributer().getId());
		super.update(account);
	}

	@Override
	public void saveOrUpdate(CapitalAccount account) {
		if(null != account.getIsDefault() && account.getIsDefault())
			capitalAccountDao.cancelDefaultAccount(account.getDistributer().getId());
		super.saveOrUpdate(account);
	}

	@Override
	public void modify(CapitalAccount source, CapitalAccount target) throws IllegalArgumentException, IllegalAccessException {
		if(target.getIsDefault())
			capitalAccountDao.cancelDefaultAccount(source.getDistributer().getId());
		super.modify(source, target);
	}

}
