package com.yinzhiwu.springmvc3.entity.type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="recordType")
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
	
	public RecordType(String name){
		super();
		super.setName(name);
	}
	
	public RecordType(String name, String comments, float factor){
		super();
		super.setName(name);
		this.comments= comments;
		this.factor= factor;
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
