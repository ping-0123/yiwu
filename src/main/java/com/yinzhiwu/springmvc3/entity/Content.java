package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Entity;

import com.mysql.jdbc.Blob;

@Entity
public class Content extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117870677974148746L;

	private Blob content;

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}
	
	
	
}
