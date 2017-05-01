package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RecordType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2538565482796386381L;
	
	private float factor;
	
	@Column(length=50)
	private String comments;
	

	public RecordType() {
		super();
	}

	public float getFactor() {
		return factor;
	}



	public void setFactor(float factor) {
		this.factor = factor;
	}



	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
