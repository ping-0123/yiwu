package com.test.spring.event;

/**
*@Author ping
*@Time  创建时间:2017年10月27日下午3:08:35
*
*/

public class BlogModifiedEvent {
	
	private final Blog blog;
	private final boolean importantChange;
	
	
	
	public BlogModifiedEvent(Blog blog, boolean importantChange) {
		this.blog = blog;
		this.importantChange = importantChange;
	}
	
	
	public Blog getBlog() {
		return blog;
	}
	public boolean isImportantChange() {
		return importantChange;
	}
	
	
}
