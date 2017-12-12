package com.yinzhiwu.yiwu.exception.business;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月29日 下午9:50:22
*
*/

@SuppressWarnings("serial")
public class WithdrawBrokerageException extends BusinessException {

	public WithdrawBrokerageException() {
		super();
	}

	public WithdrawBrokerageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WithdrawBrokerageException(String message, Throwable cause) {
		super(message, cause);
	}

	public WithdrawBrokerageException(String message) {
		super(message);
	}

	public WithdrawBrokerageException(Throwable cause) {
		super(cause);
	}

	
}
