package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.CapitalAccount;

public interface CapitalAccountDao extends IBaseDao<CapitalAccount, Integer> {

	List<CapitalAccount> findByDistributerId(int distributerId);

	List<CapitalAccount> findByTypeAndDistributerId(int accountTypeId, int distributerId);

}
