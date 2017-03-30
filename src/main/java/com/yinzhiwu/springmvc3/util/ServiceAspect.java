package com.yinzhiwu.springmvc3.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class ServiceAspect {

	public void before(){
		System.out.println("ServiceAspect before.....");
	}
	
	public  void after() {
		System.out.println("ServiceAspect after.....");
	}
	
	public void afterReturn(){
		System.out.println("ServiceAspect afterReturn.....");
	}
	
	public void afterThrow(){
		System.out.println("ServiceAspect afterThrow.....");
	}
	
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		Object obj= null;
		int num=0;
		System.out.println("ServiceAspect around start....." + num++);
		obj= pjp.proceed();
		System.out.println("ServiceAspect around stoped....." + obj +"    " + num++ );
		return obj;
		
	}
}
