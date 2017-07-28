package com.yinzhiwu.yiwu.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.enums.Gender;

public class DistributerRegisterModel {

	@Pattern(regexp = "^1\\d{10}$", message = "请输入正确的11位手机号码")
	private String phoneNo;

	private String username;

	@Pattern(regexp = "^[a-zA-Z]\\w{5,17}$", message = "密码必须由字母开头，有字母，数字，下划线组成的6-18位字符")
	private String password;

	private String name;

	private String nickName;

	@NotNull
	private Gender gender;

	// @Past
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDay;

	private Integer followedByStoreId;

	@Size(min = 10, max = 28)
	private String wechatNo;

	private String invitationCode;
	
	public Distributer toDistributer(){
		Distributer distributer = new Distributer();
		distributer.setPhoneNo(this.phoneNo);
		distributer.setUsername(this.username);
		distributer.setPassword(this.password);
		distributer.setName(this.name);
		distributer.setNickName(this.nickName);
		distributer.setGender(this.gender);
		distributer.setBirthday(this.birthDay);
		distributer.setWechatNo(this.wechatNo);
		if(followedByStoreId != null){
			DepartmentYzw store = new DepartmentYzw();
			store.setId(this.followedByStoreId);
			distributer.setFollowedByStore(store);
		}
		return distributer;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public Gender getGender() {
		return gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public Integer getFollowedByStoreId() {
		return followedByStoreId;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setUsername(String account) {
		this.username = account;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public void setFollowedByStoreId(int followedByStoreId) {
		this.followedByStoreId = followedByStoreId;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
