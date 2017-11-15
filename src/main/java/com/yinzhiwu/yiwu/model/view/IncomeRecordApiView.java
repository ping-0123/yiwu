package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;

@MapedClass(IncomeRecord.class)
public class IncomeRecordApiView {

	private static Logger LOG = LoggerFactory.getLogger(IncomeRecordApiView.class);

	private int id;

	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date date;

	private String eventTypeName;

	private String memberName;

	private String memberId;

	private String phoneNo;
	
	private String customerName;

	private String superMemberName;

	private String incomeTypeName;

	private float incomeValue;

	private float payedAmount;

	private float currentValue;

	private float factor;

	public IncomeRecordApiView() {
	}

	public IncomeRecordApiView(IncomeRecord r) {
		Assert.notNull(r);
		Assert.notNull(r.getContributor());

		this.id = r.getId();
		this.date = r.getRecordTimestamp();
		try {
//			this.eventTypeName = r.getIncomeEvent().getType().getName();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		this.phoneNo = r.getContributor().getPhoneNo();
		this.customerName = r.getContributor().getCustomer().getName();
		this.memberId = r.getContributor().getMemberCard();
		this.memberName = r.getContributor().getName();
		if (r.getContributor().getSuperDistributer() != null)
			this.superMemberName = r.getContributor().getSuperDistributer().getName();
		if (r.getIncomeType() != null)
			this.incomeTypeName = r.getIncomeType().getName();
		this.incomeValue = r.getIncomeValue();
		this.payedAmount = r.getContributedValue();
		this.currentValue = r.getCurrentValue();
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

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	public IncomeRecordApiView(int id, Date date, String eventTypeName) {
		this.id = id;
		this.date = date;
		this.eventTypeName = eventTypeName;
	}

	public IncomeRecordApiView(int id, Date date, String eventTypeName, String memberName, String memberId,
			 String phoneNo, String customerName,
			String superMemberName, String incomeTypeName, float incomeValue, float payedAmount, float currentValue,
			float factor) {
		super();
		this.id = id;
		this.date = date;
		this.eventTypeName = eventTypeName;
		this.memberName = memberName;
		this.memberId = memberId;
		this.phoneNo = phoneNo;
		this.customerName  = customerName;
		this.superMemberName = superMemberName;
		this.incomeTypeName = incomeTypeName;
		this.incomeValue = incomeValue;
		this.payedAmount = payedAmount;
		this.currentValue = currentValue;
		this.factor = factor;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
