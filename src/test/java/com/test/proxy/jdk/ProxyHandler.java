package com.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
*@Author ping
*@Time  创建时间:2017年11月17日下午5:00:03
*
*/

public class ProxyHandler implements InvocationHandler{
	
	private Object proxied;
	
	

	public ProxyHandler(Object proxied) {
		super();
		this.proxied = proxied;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy argment is " + proxy.getClass());
		System.out.println("before .......");
		Object result = method.invoke(proxied, args);
		System.out.println("after ......");
		return result;
	}

}
