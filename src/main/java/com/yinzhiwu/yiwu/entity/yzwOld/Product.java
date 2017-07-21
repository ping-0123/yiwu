package com.yinzhiwu.yiwu.entity.yzwOld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vproduct")
public class Product {

	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(length=32)
	private String name;
	
	@Column(name="card_type",length=32)
	private String cardType;
	
	@Column(name="customer_type", length=32)
	private String customerType;
	
	@Column(name="marked_price")
	private Integer markedPrice;
	
	@Column(name="useful_life")
	private Short usefulLife;
	
	@Column(name="useful_times")
	private Short usefulTimes;
	
	@Column(name="obsolete_flag")
	private Boolean isObsolete;
	
	@Column(name="DY_RCP", length=32)
	private String dyRCP;
	
	@Column(name="max_leave_times")
	private Short maxLeaveTimes;
	
	@Column(name="sf_create_user")
	private Integer createUserId;
	
	@Column(name="sf_last_change_user")
	private Integer lastChangeUserId;
	
	@Column(name="sf_create_time")
	private Date createTime;
	
	@Column(name="sf_last_change_time")
	private Date lastChangeTime;
	
	@Column(name="sf_last_change_timeStamp")
	private Date lastChangeTimestamp;
	
	@Column(name="machineCode")
	private Integer machineCode;
	
	@Column(name="sf_last_sync_timeStamp")
	private Date lastSyncTimeStamp;
	
	
	

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getCardType() {
		return cardType;
	}

	public final void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public final String getCustomerType() {
		return customerType;
	}

	public final void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public final Integer getMarkedPrice() {
		return markedPrice;
	}

	public final void setMarkedPrice(Integer markedPrice) {
		this.markedPrice = markedPrice;
	}

	public final Short getUsefulLife() {
		return usefulLife;
	}

	public final void setUsefulLife(Short usefulLife) {
		this.usefulLife = usefulLife;
	}

	public final Short getUsefulTimes() {
		return usefulTimes;
	}

	public final void setUsefulTimes(Short usefulTimes) {
		this.usefulTimes = usefulTimes;
	}

	public final Boolean getIsObsolete() {
		return isObsolete;
	}

	public final void setIsObsolete(Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	public final String getDyRCP() {
		return dyRCP;
	}

	public final void setDyRCP(String dyRCP) {
		this.dyRCP = dyRCP;
	}

	public final Short getMaxLeaveTimes() {
		return maxLeaveTimes;
	}

	public final void setMaxLeaveTimes(Short maxLeaveTimes) {
		this.maxLeaveTimes = maxLeaveTimes;
	}

	public final Integer getCreateUserId() {
		return createUserId;
	}

	public final void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public final Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	public final void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	public final Date getCreateTime() {
		return createTime;
	}

	public final void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final Date getLastChangeTime() {
		return lastChangeTime;
	}

	public final void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public final Date getLastChangeTimestamp() {
		return lastChangeTimestamp;
	}

	public final void setLastChangeTimestamp(Date lastChangeTimestamp) {
		this.lastChangeTimestamp = lastChangeTimestamp;
	}

	public final Integer getMachineCode() {
		return machineCode;
	}

	public final void setMachineCode(Integer machineCode) {
		this.machineCode = machineCode;
	}

	public final Date getLastSyncTimeStamp() {
		return lastSyncTimeStamp;
	}

	public final void setLastSyncTimeStamp(Date lastSyncTimeStamp) {
		this.lastSyncTimeStamp = lastSyncTimeStamp;
	}
	
	
	
}
