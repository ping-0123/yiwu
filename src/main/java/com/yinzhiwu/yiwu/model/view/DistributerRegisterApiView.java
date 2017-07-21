package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Distributer;

public class DistributerRegisterApiView {

	@JsonFormat(pattern="yyyy/MM/dd")
	private Date registerDate;
	
	private String memberId;
	
	private String memberName;
	
	private String superDistributerName;
	
	/**
	 * 收益者所获得的经验
	 */
	private float exp;
	
	
	public DistributerRegisterApiView(){}
	
	public DistributerRegisterApiView(Distributer d, float exp){
		if(d==null)
			return;
		this.registerDate = d.getRegistedTime();
		this.memberId = d.getMemberId();
		this.memberName =d.getName();
		this.superDistributerName = d.getSuperDistributer()==null? null:d.getSuperDistributer().getName();
		this.exp = exp;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getSuperDistributerName() {
		return superDistributerName;
	}

	public float getExp() {
		return exp;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setMemberId(String memeberId) {
		this.memberId = memeberId;
	}

	public void setMemberName(String memeberName) {
		this.memberName = memeberName;
	}

	public void setSuperDistributerName(String superDistributerName) {
		this.superDistributerName = superDistributerName;
	}

	public void setExp(float exp) {
		this.exp = exp;
	}
	
}
