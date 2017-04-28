package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class RecordType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2538565482796386381L;
	
	private float factor;
	
	private List<AbstractRecord> records = new ArrayList<>();

	public RecordType() {
		super();
	}

	public float getFactor() {
		return factor;
	}

	@OneToMany(mappedBy="recordType")
	public List<AbstractRecord> getRecords() {
		return records;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public void setRecords(List<AbstractRecord> records) {
		this.records = records;
	}
	
	
	
}
