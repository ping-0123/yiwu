package com.yinzhiwu.springmvc3.entity.yzw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vproduct")
public class ProductYzw extends BaseYzwEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1893100957719617919L;

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

//	@OneToMany(mappedBy="product")
//	private List<OrderYzw> orders = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCardType() {
		return cardType;
	}

	public String getCustomerType() {
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

	public Boolean getIsObsolete() {
		return isObsolete;
	}

	public String getDyRCP() {
		return dyRCP;
	}

	public Short getMaxLeaveTimes() {
		return maxLeaveTimes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public void setCustomerType(String customerType) {
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

	public void setIsObsolete(Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	public void setDyRCP(String dyRCP) {
		this.dyRCP = dyRCP;
	}

	public void setMaxLeaveTimes(Short maxLeaveTimes) {
		this.maxLeaveTimes = maxLeaveTimes;
	}

	public ProductYzw() {
		super();
	}
	

	
}
