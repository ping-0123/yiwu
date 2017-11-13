package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.enums.PaymentMode;

public interface CapitalAccountDao extends IBaseDao<CapitalAccount, Integer> {

	List<CapitalAccount> findByDistributerId(int distributerId);

	List<CapitalAccount> findByDistributerIdAndPaymentMode(Integer id, PaymentMode paymentMode);

}
