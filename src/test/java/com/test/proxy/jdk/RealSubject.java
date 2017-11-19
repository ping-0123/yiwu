package com.test.proxy.jdk;

/**
*@Author ping
*@Time  创建时间:2017年11月17日下午4:58:51
*
*/

public class RealSubject implements Subject {

	@Override
	public void doSomething() {
		System.out.println("call dosomething()");
	}
	

}
