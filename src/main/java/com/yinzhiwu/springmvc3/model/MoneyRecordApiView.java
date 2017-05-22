package com.yinzhiwu.springmvc3.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;
import com.yinzhiwu.springmvc3.enums.MoneyRecordCategory;
import com.yinzhiwu.springmvc3.util.MoneyRecordCategoryUtil;



public class MoneyRecordApiView {

	@Min(0)
	private int benificiaryId;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date date;
	
	@NotNull
	private MoneyRecordCategory category;
	
	private float amount;
	
	private float currentBrockerage;
			
	private float currentFunds;
	
	public MoneyRecordApiView(MoneyRecord m)
	{
		this.benificiaryId = m.getBeneficiaty().getId();
		this.date = m.getContributedDate();
		this.category = MoneyRecordCategoryUtil
				.toMoneyRecordCategory(m.getRecordType().getId());
		this.amount = m.getIncome();
		this.currentBrockerage= m.getCurrentBrokerage();
		this.currentFunds = m.getCurrentFunds();
	}
	 

	public int getBenificiaryId() {
		return benificiaryId;
	}

	public Date getDate() {
		return date;
	}

	public MoneyRecordCategory getCategory() {
		return category;
	}

	public float getAmount() {
		return amount;
	}

	public float getCurrentBrockerage() {
		return currentBrockerage;
	}

	public float getCurrentFunds() {
		return currentFunds;
	}

	public void setBenificiaryId(int benificiaryId) {
		this.benificiaryId = benificiaryId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCategory(MoneyRecordCategory category) {
		this.category = category;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setCurrentBrockerage(float currentBrockerage) {
		this.currentBrockerage = currentBrockerage;
	}

	public void setCurrentFunds(float currentFunds) {
		this.currentFunds = currentFunds;
	}

	public MoneyRecordApiView() {
		super();
	}
	

}
