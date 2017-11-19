package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

@Repository
public class CapitalAccountDaoImpl extends BaseDaoImpl<CapitalAccount, Integer> implements CapitalAccountDao {

	@Override
	public List<CapitalAccount> findByDistributerId(int distributerId) {
		return findByProperty("distributer.id", distributerId);
	}

	@Override
	public List<CapitalAccount> findByDistributerIdAndPaymentMode(Integer distributerId, PaymentMode paymentMode) {
		return findByProperties(
				new String[]{"distributer.id","paymentMode"}, 
				new Object[]{distributerId,paymentMode});
	}
	
	@Override
	public CapitalAccount findOneByDistributerIdAndPaymentMode(Integer distributerId, PaymentMode paymentMode) {
		try {
			return findOneByProperties(new String[]{"distributer.id","paymentMode"}, 
					new Object[]{distributerId,paymentMode});
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	
	
	private void cancelDefaulAccountExclude(CapitalAccount account){
		List<CapitalAccount> acounts = findByDistributerId(account.getDistributer().getId());
		acounts.remove(account);
		logger.info("distributer " + account.getDistributer().getId() + " has " + acounts.size() + " accounts");
		for (CapitalAccount capitalAccount : acounts) {
			logger.info("set account to non default");
			capitalAccount.setIsDefault(Boolean.FALSE);
			getSession().update(capitalAccount);
		}
	}
	
	@Transactional
	public void cancelDefaultAccount(Integer distributerId) {
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE CapitalAccount");
		hql.append(" SET isDefault = false");
		hql.append(" WHERE distributer.id =:distributerId");
		
		
		getSession().createQuery(hql.toString())
			.setParameter("distributerId", distributerId)
			.executeUpdate();
		
	}

	@Override
	public Integer save(CapitalAccount entity) {
		entity.init();
		if(entity.getIsDefault())
			//TODO 为什Junit测试的时候用 cancelDefaultAccount(Integer)没问题， 而用 cancelDefaul
			cancelDefaulAccountExclude(entity);
		return (Integer) getSession().save(entity);
	}

	@Override
	public void update(CapitalAccount entity) {
		if(entity.getIsDefault())
			cancelDefaultAccount(entity.getDistributer().getId());
		entity.beforeUpdate();
		getSession().update(entity);
	}

	@Override
	public void modify(CapitalAccount source, CapitalAccount target)
			throws IllegalArgumentException, IllegalAccessException {
		if(!source.getIsDefault() && null != target.getIsDefault() && target.getIsDefault())
			cancelDefaultAccount(source.getDistributer().getId());
		super.modify(source, target);
	}

	@Override
	public void saveOrUpdate(CapitalAccount entity) {
		if(null == entity.getId()){
			entity.init();
		}
		if(entity.getIsDefault())
			cancelDefaultAccount(entity.getDistributer().getId());
		super.saveOrUpdate(entity);
	}

	
	


}
