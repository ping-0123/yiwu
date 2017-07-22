package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TopThreeApiView {

	private String distributerName;
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date registerDate;
	private float sumBrokerageIncome;
	private int sumShareTweetTimes;
	private int sumMemberCount;
	private int sumOrderCount;
	private String headIconUrl;
	
	
	public String getDistributerName() {
		return distributerName;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public float getSumBrokerageIncome() {
		return sumBrokerageIncome;
	}
	public int getSumShareTweetTimes() {
		return sumShareTweetTimes;
	}
	public int getSumMemberCount() {
		return sumMemberCount;
	}
	public int getSumOrderCount() {
		return sumOrderCount;
	}
	public void setDistributerName(String distributerName) {
		this.distributerName = distributerName;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public void setSumBrokerageIncome(float sumBrokerageIncome) {
		this.sumBrokerageIncome = sumBrokerageIncome;
	}
	public void setSumShareTweetTimes(int sumShareTweetTimes) {
		this.sumShareTweetTimes = sumShareTweetTimes;
	}
	public void setSumMemberCount(int sumMemberCount) {
		this.sumMemberCount = sumMemberCount;
	}
	public void setSumOrderCount(int sumOrderCount) {
		this.sumOrderCount = sumOrderCount;
	}
	public String getHeadIconUrl() {
		return headIconUrl;
	}
	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}
	
	
	
}
