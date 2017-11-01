package com.yinzhiwu.yiwu.web.purchase.dto;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractTypeYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.enums.CourseType;

/**
*@Author ping
*@Time  创建时间:2017年7月28日上午10:39:40
*
*/

public class ProductDto {
	
	private Integer id;
	private String name;
	private ProductCardType cardType;
	private CustomerAgeType customerType;
	private Integer markedPrice;
	private Short usefulLife;
	private Short usefulTimes;
	private Float usefulHours;
	private Integer contractTypeId;
	private CourseType contractType;
	
	public ProductDto(){}
	public ProductDto(ProductYzw p){
		this.id = p.getId();
		this.name = p.getName();
		this.cardType = p.getCardType();
		this.customerType = p.getCustomerType();
		this.markedPrice = p.getMarkedPrice();
		this.usefulLife = p.getUsefulLife();
		this.usefulTimes = p.getUsefulTimes();
		this.usefulHours = p.getUsefulHours();
		ElectricContractTypeYzw eContractType = p.getContractType();
		if(eContractType != null){
			this.contractTypeId = eContractType.getId();
			this.contractType = eContractType.getContractType();
		}
	}
	
	public ProductDto(Integer id, String name, ProductCardType cardType, CustomerAgeType customerType,
			Integer markedPrice, Short usefulLife, Short usefulTimes, Float usefulHours, Integer contractTypeId,
			CourseType contractTypeName) {
		this.id = id;
		this.name = name;
		this.cardType = cardType;
		this.customerType = customerType;
		this.markedPrice = markedPrice;
		this.usefulLife = usefulLife;
		this.usefulTimes = usefulTimes;
		this.usefulHours = usefulHours;
		this.contractTypeId = contractTypeId;
		this.contractType = contractTypeName;
	}
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public ProductCardType getCardType() {
		return cardType;
	}
	public CustomerAgeType getCustomerType() {
		return customerType;
	}
	public Integer getMarkedPrice() {
		return markedPrice;
	}
	public Short getUsefulLife() {
		return usefulLife;
	}
	public Short getUsefulTimes() {
		return usefulTimes;
	}
	public Float getUsefulHours() {
		return usefulHours;
	}
	public Integer getContractTypeId() {
		return contractTypeId;
	}
	public CourseType getContractTypeName() {
		return contractType;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCardType(ProductCardType cardType) {
		this.cardType = cardType;
	}
	public void setCustomerType(CustomerAgeType customerType) {
		this.customerType = customerType;
	}
	public void setMarkedPrice(Integer markedPrice) {
		this.markedPrice = markedPrice;
	}
	public void setUsefulLife(Short usefulLife) {
		this.usefulLife = usefulLife;
	}
	public void setUsefulTimes(Short usefulTimes) {
		this.usefulTimes = usefulTimes;
	}
	public void setUsefulHours(Float usefulHours) {
		this.usefulHours = usefulHours;
	}
	public void setContractTypeId(Integer contractTypeId) {
		this.contractTypeId = contractTypeId;
	}
	public void setContractTypeName(CourseType contractTypeName) {
		this.contractType = contractTypeName;
	}
	
	
	
}
