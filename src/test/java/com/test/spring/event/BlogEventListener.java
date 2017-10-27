package com.test.spring.event;

import org.springframework.context.event.EventListener;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:10:39
*
*/

public class BlogEventListener {
	
	
	@EventListener
	public void blogModified(BlogModifiedEvent event){
		System.out.println();
	}
}
