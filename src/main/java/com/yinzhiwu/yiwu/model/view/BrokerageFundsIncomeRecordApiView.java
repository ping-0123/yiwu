package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;

public class BrokerageFundsIncomeRecordApiView {
	
	private static Log LOG = LogFactory.getLog(BrokerageFundsIncomeRecordApiView.class);

	@JsonFormat(pattern="yyyy/MM/dd")
	private Date date;
	
	private String category;
	
	private float incomeValue;
	
	private float currentValue;
	
	private String incomeTypeName;
	
	public BrokerageFundsIncomeRecordApiView() {
	}
	
	public BrokerageFundsIncomeRecordApiView(IncomeRecord r) {
		this.date = r.getRecordTimestamp();
		try{this.category = r.getIncomeEvent().getType().getName();
		}catch (Exception e) {
			LOG.error(e.getMessage());}
		this.incomeValue = r.getIncomeValue();
		this.currentValue = r.getCurrentValue();
		try{this.incomeTypeName=r.getIncomeType().getName();
		}catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	public Date getDate() {
		return date;
	}

	public String getCategory() {
		return category;
	}

	public float getIncomeValue() {
		return incomeValue;
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public String getIncomeTypeName() {
		return incomeTypeName;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setIncomeValue(float incomeValue) {
		this.incomeValue = incomeValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	public void setIncomeTypeName(String incomeTypeName) {
		this.incomeTypeName = incomeTypeName;
	}

	
	
}
