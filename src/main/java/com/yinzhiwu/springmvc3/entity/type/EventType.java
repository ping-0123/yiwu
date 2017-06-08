package com.yinzhiwu.springmvc3.entity.type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.yinzhiwu.springmvc3.entity.IncomeFactor;

@Entity
@DiscriminatorValue("EventType")
public class EventType  extends BaseType{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049815767098788287L;
	
	private List<IncomeFactor> incomeFactors = new ArrayList<>();

	public List<IncomeFactor> getIncomeFactors() {
		return incomeFactors;
	}

	public void setIncomeFactors(List<IncomeFactor> incomeFactors) {
		this.incomeFactors = incomeFactors;
	}
	
	

}
