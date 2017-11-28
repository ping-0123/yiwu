package com.yinzhiwu.yiwu.entity.yzw;

import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author ping
 * @date 2017年11月28日上午10:30:10
 * @since v2.1.5
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="vcustomer_follow")
public class CustomerFollow extends BaseYzwEntity{
	
	private Integer id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private CustomerYzw customer;
	
	private FollowType type;
	
	private String description;
	
	private String picture;
	
	private Date nextDate;
	
	private Date acturalDate;
	
	
	
	public Integer getId() {
		return id;
	}



	public CustomerYzw getCustomer() {
		return customer;
	}



	public FollowType getType() {
		return type;
	}



	public String getDescription() {
		return description;
	}



	public String getPicture() {
		return picture;
	}



	public Date getNextDate() {
		return nextDate;
	}



	public Date getActuralDate() {
		return acturalDate;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setCustomer(CustomerYzw customer) {
		this.customer = customer;
	}



	public void setType(FollowType type) {
		this.type = type;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}



	public void setActuralDate(Date acturalDate) {
		this.acturalDate = acturalDate;
	}



	public enum FollowType{
		TELEPHONE,
		QQ,
		WECHAT,
	}
}
