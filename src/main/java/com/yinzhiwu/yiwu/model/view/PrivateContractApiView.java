package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
*@Author ping
*@Time  创建时间:2017年8月16日下午4:11:33
*
*/

public class PrivateContractApiView {
	
	private String contractNo;
	private String productName;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date start;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date end;
	public PrivateContractApiView() {
	}
	public PrivateContractApiView(String contractNo, String productName, Date start, Date end) {
		this.contractNo = contractNo;
		this.productName = productName;
		this.start = start;
		this.end = end;
	}
	
	public String getContractNo() {
		return contractNo;
	}
	public String getProductName() {
		return productName;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
	
}
