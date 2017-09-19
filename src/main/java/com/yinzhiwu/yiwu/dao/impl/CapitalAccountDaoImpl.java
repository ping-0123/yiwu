package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;

@Repository
public class CapitalAccountDaoImpl extends BaseDaoImpl<CapitalAccount, Integer> implements CapitalAccountDao {

	@Override
	public List<CapitalAccount> findByDistributerId(int distributerId) {
		return findByProperty("distributer.id", distributerId);
	}

	@Override
	public List<CapitalAccount> findByTypeAndDistributerId(int accountTypeId, int distributerId) {
		return findByProperties(new String[]{"capitalAccountType.id", "distributer.id"}, 
				new Object[]{accountTypeId, distributerId}) ;
	}

}
