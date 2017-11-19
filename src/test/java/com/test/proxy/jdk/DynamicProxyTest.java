package com.test.proxy.jdk;

import java.lang.reflect.Proxy;

/**
*@Author ping
*@Time  创建时间:2017年11月17日下午5:02:34
*
*/

public class DynamicProxyTest {
	
	public static void main(String[] args) {
		Subject real = new RealSubject();
		Object proxy = Proxy.newProxyInstance(
				Subject.class.getClassLoader(), 
				new Class[]{Subject.class}, 
				new ProxyHandler(real));
		
		Object logProxy = Proxy.newProxyInstance(
				Subject.class.getClassLoader(),
				new Class[]{Subject.class},
				new LogProxyHandler(proxy));
		
		((Subject)logProxy).doSomething();
	}
}
