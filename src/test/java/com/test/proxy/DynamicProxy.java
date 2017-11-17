package com.test.proxy;

import java.lang.reflect.Proxy;

/**
*@Author ping
*@Time  创建时间:2017年11月17日下午5:02:34
*
*/

public class DynamicProxy {
	
	public static void main(String[] args) {
		Subject real = new RealSubject();
		Subject proxySubject = (Subject) Proxy.newProxyInstance(
				Subject.class.getClassLoader(), new Class[]{RealSubject.class}, new ProxyHandler(real));
		
		proxySubject.doSomething();
	}
}
