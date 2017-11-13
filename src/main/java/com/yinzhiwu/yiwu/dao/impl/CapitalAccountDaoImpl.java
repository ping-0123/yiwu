package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.enums.PaymentMode;

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
	
	

	private void cancelDefaultAccount(Integer distributerId) {
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE CapitalAccount");
		hql.append(" SET isDefault = false");
		hql.append(" WHERE distributer.id =:distributerId");
		
		getSession().createQuery(hql.toString())
			.setParameter("distributerId", distributerId)
			.executeUpdate();
	}

	@Override
	public Integer save(CapitalAccount account) {
		if(null != account.getIsDefault() && account.getIsDefault())
			cancelDefaultAccount(account.getDistributer().getId());
		return super.save(account);
	}

	@Override
	public void update(CapitalAccount account) {
		if(null != account.getIsDefault() && account.getIsDefault())
			cancelDefaultAccount(account.getDistributer().getId());
		super.update(account);
	}

	@Override
	public void saveOrUpdate(CapitalAccount account) {
		if(null != account.getIsDefault() && account.getIsDefault())
			cancelDefaultAccount(account.getDistributer().getId());
		super.saveOrUpdate(account);
	}

	@Override
	public void modify(CapitalAccount source, CapitalAccount target) throws IllegalArgumentException, IllegalAccessException {
		if(target.getIsDefault())
			cancelDefaultAccount(source.getDistributer().getId());
		super.modify(source, target);
	}


}
