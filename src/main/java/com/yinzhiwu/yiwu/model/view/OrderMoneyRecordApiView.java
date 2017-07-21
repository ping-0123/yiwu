package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMoneyRecordApiView {

	@JsonFormat(pattern="yyyy/MM/dd")
	private Date payedDate;
	
	private String memberName;
	
	private String superDistributerName;
	
	private float orderAmount;
	
	private float promotionRate;
	
	private float inComeBrokerage;
	
	public OrderMoneyRecordApiView(){}
	
//	public OrderMoneyRecordApiView(MoneyRecord r){
//		if(r==null)
//			return;
//		Distributer contributer = r.getContributor();
//		if(contributer==null)
//			return;
//		this.memberName = contributer.getName();
//		this.superDistributerName = contributer.getSuperDistributer()==null?
//				null: contributer.getSuperDistributer().getName();
//		OrderYzw order = r.getOrder();
//		if(order !=null){
//			this.payedDate = order.getPayedDate();
//		}else
//			this.payedDate = r.getContributedDate();
//		
//		this.orderAmount = r.getContributedValue();
//		this.promotionRate=r.getIncomeFactor();
//		this.inComeBrokerage = r.getIncome();
//		
//		
//	}

	public Date getPayedDate() {
		return payedDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getSuperDistributerName() {
		return superDistributerName;
	}

	public float getOrderAmount() {
		return orderAmount;
	}

	public float getPromotionRate() {
		return promotionRate;
	}


	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setSuperDistributerName(String superDistributerName) {
		this.superDistributerName = superDistributerName;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setPromotionRate(float promotionRate) {
		this.promotionRate = promotionRate;
	}

	public float getInComeBrokerage() {
		return inComeBrokerage;
	}

	public void setInComeBrokerage(float inComeBrokerage) {
		this.inComeBrokerage = inComeBrokerage;
	}


	
}
