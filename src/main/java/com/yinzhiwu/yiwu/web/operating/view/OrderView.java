package com.yinzhiwu.yiwu.web.operating.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月26日 下午4:28:58
*
*/

@MapedClass(OrderYzw.class)
public class OrderView {
	
	private String id;
	
	@MapedProperty("product.id")
	private Integer productId;
	
	@MapedProperty("product.name")
	private String productName;
	
	private Float markedPrice;
	
	private Integer count;
	
	private Float discount;
	
	private Float payedAmount;
	
	@MapedProperty("distributer.id")
	private Integer distributerId;
	
	@MapedProperty("distributer.name")
	private String distributerName;
	
	@MapedProperty("distributer.memberCard")
	private String distributerMemberCard;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date payedDate;
	
	@MapedProperty("store.id")
	private Integer storeId;
	
	@MapedProperty("store.name")
	private String storeName;
	
	private ContractView contract;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getMarkedPrice() {
		return markedPrice;
	}

	public void setMarkedPrice(Float markedPrice) {
		this.markedPrice = markedPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(Float payedAmount) {
		this.payedAmount = payedAmount;
	}

	public Integer getDistributerId() {
		return distributerId;
	}

	public void setDistributerId(Integer distributerId) {
		this.distributerId = distributerId;
	}

	public String getDistributerName() {
		return distributerName;
	}

	public void setDistributerName(String distributerName) {
		this.distributerName = distributerName;
	}

	public String getDistributerMemberCard() {
		return distributerMemberCard;
	}

	public void setDistributerMemberCard(String distributerMemberCard) {
		this.distributerMemberCard = distributerMemberCard;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public ContractView getContract() {
		return contract;
	}

	public void setContract(ContractView contract) {
		this.contract = contract;
	}
	
	public static final class OrderViewConverter extends AbstractConverter<OrderYzw, OrderView>{
		public static final OrderViewConverter INSTANCE = new OrderViewConverter();
	}
	
}
