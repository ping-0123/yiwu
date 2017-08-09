package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.type.TweetType;

public class TweetTypeApiView {

	private int id;

	private String name;

	public TweetTypeApiView() {
	}

	public TweetTypeApiView(TweetType t) {
		this.id = t.getId();
		this.name = t.getName();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
