package com.yinzhiwu.springmvc3.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@JsonIgnore
	private MultipartFile image;
	
	
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

	public MultipartFile getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExpGradeNo(int expGradeNo) {
		this.expGradeNo = expGradeNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public void setNeededExpForUpdate(float neededExpForUpdate) {
		this.neededExpForUpdate = neededExpForUpdate;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	
}
