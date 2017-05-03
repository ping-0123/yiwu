package com.yinzhiwu.springmvc3.model;

import java.io.Serializable;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.util.UrlUtil;

public class DistributerApiView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063578788280665376L;

	private int id;
	
	private int expGradeNo;
	
	private String name;
	
	private String nickName;
	
	private String memeberId;
	
	private String shareCode;
	
	private String headIconUrl;
	
	private float neededExpForUpdate;
	
	
	
	private float beatRate;

	public DistributerApiView() {
	}
	
	public DistributerApiView(Distributer d){
		this.id = d.getId();
		this.expGradeNo = d.getExpGrade().getGradeNo();
		this.name = d.getName();
		this.nickName = d.getNickName();
		this.memeberId = d.getMemberId();
		this.shareCode = d.getShareCode();
		this.headIconUrl = UrlUtil.toHeadIcomUrl(d.getHeadIconName());
		this.neededExpForUpdate = d.getExpGrade().getUpgradeExp()-d.getExp();
	}

	public float getBeatRate() {
		return beatRate;
	}

	public void setBeatRate(float beatRate) {
		this.beatRate = beatRate;
	}

	public int getId() {
		return id;
	}

	public int getExpGradeNo() {
		return expGradeNo;
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public String getMemeberId() {
		return memeberId;
	}

	public String getShareCode() {
		return shareCode;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public float getNeededExpForUpdate() {
		return neededExpForUpdate;
	}
	
	
}
