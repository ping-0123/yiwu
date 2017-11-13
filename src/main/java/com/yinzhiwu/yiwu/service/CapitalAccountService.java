package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.enums.PaymentMode;

public interface CapitalAccountService extends IBaseService<CapitalAccount, Integer> {

	List<CapitalAccount> findByDistributerId(int distributerId);

	List<CapitalAccount> findByDistributerAndPaymentMode(Distributer distributer, PaymentMode paymentMode);

}
