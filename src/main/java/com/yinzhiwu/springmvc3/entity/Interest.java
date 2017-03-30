package com.yinzhiwu.springmvc3.entity;

public class Interest {
	
	private Department store;
	
	private Dance dance;
	
	private String openOrClose;
	
	private Employee interestedTeacher;

	public final Department getStore() {
		return store;
	}

	public final void setStore(Department store) {
		this.store = store;
	}

	public final String getOpenOrClose() {
		return openOrClose;
	}

	public final void setOpenOrClose(String openOrClose) {
		this.openOrClose = openOrClose;
	}

	public final Employee getInterestedTeacher() {
		return interestedTeacher;
	}

	public final void setInterestedTeacher(Employee interestedTeacher) {
		this.interestedTeacher = interestedTeacher;
	}

	public final Dance getDance() {
		return dance;
	}

	public final void setDance(Dance dance) {
		this.dance = dance;
	}
	
	
}
