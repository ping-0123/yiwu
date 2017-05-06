package com.yinzhiwu.springmvc3.model;

import java.io.Serializable;

public class SumRecordApiView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1893739499845022068L;

	private DistributerApiView distributerApiView;
	
	private float accumulativeBrokerage;
	
	private int myShareTweetTimes;
	
	private int subordinateShareTweetTimes;
	
	private int subordinateCount;
	
	private int secondaryCount;
	
	private int subordinateOrderCount;
	
	private int secondaryOrderCount;



	public int getMyShareTweetTimes() {
		return myShareTweetTimes;
	}

	public int getSubordinateShareTweetTimes() {
		return subordinateShareTweetTimes;
	}

	public int getSubordinateCount() {
		return subordinateCount;
	}

	public int getSecondaryCount() {
		return secondaryCount;
	}

	public int getSubordinateOrderCount() {
		return subordinateOrderCount;
	}

	public int getSecondaryOrderCount() {
		return secondaryOrderCount;
	}


	public void setMyShareTweetTimes(int myShareTweetTimes) {
		this.myShareTweetTimes = myShareTweetTimes;
	}

	public void setSubordinateShareTweetTimes(int subordinateShareTweetTimes) {
		this.subordinateShareTweetTimes = subordinateShareTweetTimes;
	}

	public void setSubordinateCount(int subordinateCount) {
		this.subordinateCount = subordinateCount;
	}

	public void setSecondaryCount(int secondaryCount) {
		this.secondaryCount = secondaryCount;
	}

	public void setSubordinateOrderCount(int subordinateOrderCount) {
		this.subordinateOrderCount = subordinateOrderCount;
	}

	public void setSecondaryOrderCount(int secondaryOrderCount) {
		this.secondaryOrderCount = secondaryOrderCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DistributerApiView getDistributerApiView() {
		return distributerApiView;
	}

	public void setDistributerApiView(DistributerApiView distributerApiView) {
		this.distributerApiView = distributerApiView;
	}

	public float getAccumulativeBrokerage() {
		return accumulativeBrokerage;
	}

	public void setAccumulativeBrokerage(float accumulativeBrokerage) {
		this.accumulativeBrokerage = accumulativeBrokerage;
	}

	
	
}
