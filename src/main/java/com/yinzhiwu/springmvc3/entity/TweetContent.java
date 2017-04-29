package com.yinzhiwu.springmvc3.entity;

import java.sql.Blob;

import javax.persistence.Entity;


@Entity
public class TweetContent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117870677974148746L;

	private java.sql.Blob content;

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}
	
	
	
}
