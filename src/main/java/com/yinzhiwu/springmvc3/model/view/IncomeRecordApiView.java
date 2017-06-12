package com.yinzhiwu.springmvc3.model.view;

import java.util.Date;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;

public class IncomeRecordApiView {

	private int id;
	
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date date;
	
	private String memberName;
	
	private String memberId;
	
	private String superMemberName;
	
	private String incomeTypeName;
	
	private float incomeValue;
	
	private float payedAmount;
	
	private float factor;
	
	public IncomeRecordApiView(){}
	
	public IncomeRecordApiView(IncomeRecord r) {
		Assert.notNull(r);
		Assert.notNull(r.getContributor());
		
		this.id = r.getId();
		this.date = r.getRecordTimestamp();
		this.memberId = r.getContributor().getMemberId();
		this.memberName = r.getContributor().getName();
		if(r.getContributor().getSuperDistributer() != null)
			this.superMemberName = r.getContributor().getSuperDistributer().getName();
		if(r.getIncomeType() !=null)
			this.incomeTypeName = r.getIncomeType().getName();
		this.incomeValue = r.getIncomeValue();
		this.payedAmount = r.getContributedValue();
		this.factor = r.getIncomeFactor();
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getSuperMemberName() {
		return superMemberName;
	}


	public float getFactor() {
		return factor;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public void setSuperMemberName(String superMemberName) {
		this.superMemberName = superMemberName;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public float getIncomeValue() {
		return incomeValue;
	}

	public float getPayedAmount() {
		return payedAmount;
	}

	public void setIncomeValue(float incomeValue) {
		this.incomeValue = incomeValue;
	}

	public void setPayedAmount(float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public String getIncomeTypeName() {
		return incomeTypeName;
	}

	public void setIncomeTypeName(String incomeTypeName) {
		this.incomeTypeName = incomeTypeName;
	}
	
	
	
}
