package com.test.proxy.cglib;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月15日 上午12:04:47
*
*/

public class DoCGLib {
	
	public static void main(String[] args) {
		
		CglibProxy proxy = new CglibProxy();
		
		SayHello proxyImp = (SayHello) proxy.getProxy(SayHello.class);
		
		proxyImp.say();
	}
}
