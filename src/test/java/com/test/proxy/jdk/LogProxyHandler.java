package com.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月19日 下午5:22:03
*
*/

public class LogProxyHandler  implements InvocationHandler{
	
	private Object target;
	private static final Logger LOG = LoggerFactory.getLogger(LogProxyHandler.class);
	

	public LogProxyHandler(Object target) {
		super();
		this.target = target;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		LOG.info("before..........");
		Object retValue = method.invoke(target, args);
		LOG.info("after......");
		return retValue;
		
	}

}
