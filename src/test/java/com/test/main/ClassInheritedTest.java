package com.test.main;

import org.junit.Test;

import com.test.BaseBlockJUnitTest;
import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.dao.impl.OrderYzwDaoImpl;

public class ClassInheritedTest extends BaseBlockJUnitTest{
	
	@Test
	public void testClassInherited(){
		Class<?> a = BaseDaoImpl.class;
		Class<?> b = OrderYzwDaoImpl.class;
		if(b.isAssignableFrom(a))
			logger.info("a 是  b 的父类");
		else
			logger.info("a 不是  b 的父类");
	}
	
	public static void main(String[] args) {
		Class<?> a = BaseDaoImpl.class;
		Class<?> b = OrderYzwDaoImpl.class;
		if(b.isAssignableFrom(a))
			System.out.println("a 是  b 的父类");
		else
			System.out.println("a 不是  b 的父类");
		
	}
}
