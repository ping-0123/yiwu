package com.yinzhiwu.springmvc3.util;

import org.springframework.stereotype.Component;

@Component
public class DaoAspect {
	
	public void before(){
		System.out.println("DaoAspect before......");
	}
}
