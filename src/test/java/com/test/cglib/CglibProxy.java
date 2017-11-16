package com.test.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月14日 下午11:59:56
*
*/

public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();  
	
	public Object getProxy(@SuppressWarnings("rawtypes") Class clazz){
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("前置代理");
		
		Object result = proxy.invokeSuper(obj, args);
		
		System.out.println("后置代理");
		return result;
	}

}
