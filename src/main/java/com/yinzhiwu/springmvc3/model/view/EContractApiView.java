package com.yinzhiwu.springmvc3.model.view;

import java.text.SimpleDateFormat;


import com.yinzhiwu.springmvc3.entity.yzw.ElectricContractYzw;

public class EContractApiView {
	
	private static SimpleDateFormat DATE_FORMATE = new SimpleDateFormat("yyyy年MM月");
	
	private String contractNo;
	
	private String text;
	
	private boolean isConfirmed;
	
	public EContractApiView(){};
	
//	public EContractApiView(ElectricContractYzw e){
//		this.contractNo = e.getContractNo();
//		this.isConfirmed = e.isConfirmed();
//		StringBuilder builder = new StringBuilder();
//		builder.append("会籍合约            编号：" + e.getContractNo() + "\n");
//		builder.append("\n\n【会员资料】");
//		builder.append("\n姓名：" + e.getCustomerName()
//				+ "\t性别：" + e.getGender()
//				+ "\t出生月日：" + DATE_FORMATE.format(e.getBirthday()));
//		builder.append("\n身份证号：" + e.getIdentityCardNo());
//		builder.append("\n手机号码：" + e.getMobiePhoneNo() 
//				+ "\tQQ：" + e.getQqNo()
//				+ "\t微信：" + e.getWechatNo());
//		
//	}

	public String getContractNo() {
		return contractNo;
	}

	public String getText() {
		return text;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	
}
