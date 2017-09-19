package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.CapitalAccount;

public interface CapitalAccountService extends IBaseService<CapitalAccount, Integer> {

	List<CapitalAccount> findByDistributerId(int distributerId);

	List<CapitalAccount> findByTypeAndDistributerId(int accountTypeId, int distributerId);

}
