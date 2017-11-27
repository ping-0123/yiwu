package com.yinzhiwu.yiwu.web.operating.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Distributer.Role;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.enums.Gender;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.util.SpringUtils;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月6日 上午1:03:41
*
*/

@MapedClass(Distributer.class)
public class DistributerVO {
	
	private Integer id;
	private String memberCard;
	private String name;
	private String nickName;
	private String username;
	private String password;
	private String wechatNo;
	private String phoneNo;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date birthday;
	private Gender gender;
	private CustomerAgeType customerAgeType;
	private String shareCode;
	@MapedProperty("headIconName")
	private String headIconUrl;
	private Date registedTime;
	private Role role;
	@MapedProperty("customer.id")
	private Integer customerId;
	@MapedProperty("superDistributer.id")
	private Integer superDistributerId;
	@MapedProperty("superDistributer.name")
	private String superDistributerName;
	@MapedProperty("superDistributer.memberCard")
	private String superDistributerMemberCard;
	@MapedProperty("server.id")
	private Integer serverId;
	@MapedProperty("server.name")
	private String serverName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWechatNo() {
		return wechatNo;
	}
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public CustomerAgeType getCustomerAgeType() {
		return customerAgeType;
	}
	public void setCustomerAgeType(CustomerAgeType customerAgeType) {
		this.customerAgeType = customerAgeType;
	}
	public String getShareCode() {
		return shareCode;
	}
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	public String getHeadIconUrl() {
		return headIconUrl;
	}
	public void setHeadIconUrl(String headIconName) {
		this.headIconUrl = headIconName;
	}
	public Date getRegistedTime() {
		return registedTime;
	}
	public void setRegistedTime(Date registedTime) {
		this.registedTime = registedTime;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	

	public Integer getSuperDistributerId() {
		return superDistributerId;
	}
	public void setSuperDistributerId(Integer superDistributerId) {
		this.superDistributerId = superDistributerId;
	}
	public String getSuperDistributerName() {
		return superDistributerName;
	}
	public void setSuperDistributerName(String superDistributerName) {
		this.superDistributerName = superDistributerName;
	}
	public String getSuperDistributerMemberCard() {
		return superDistributerMemberCard;
	}
	public void setSuperDistributerMemberCard(String superDistributerMemberCard) {
		this.superDistributerMemberCard = superDistributerMemberCard;
	}
	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}



	public static final class DistributerVOConverter extends AbstractConverter<Distributer, DistributerVO>{
		public static final DistributerVOConverter INSTANCE = new DistributerVOConverter();
		
		private static final FileService fileService = SpringUtils.getBean("qiniuServiceImpl");

		@Override
		public DistributerVO fromPO(Distributer po) {
			DistributerVO vo =  super.fromPO(po);
			vo.setPassword(null);
			vo.setHeadIconUrl(fileService.generateFileUrl(po.getHeadIconName()));
			return vo;
		}
		
		
	}
}
