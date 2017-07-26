package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vorder_payed_method")
public class OrderPayedMethodYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7216339698907279296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "fk_orderPayedMethod_order_id"))
	private OrderYzw order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payedMethodId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "fk_orderPayedMethod_payedMethod_id"))
	private PayedMethodYzw payedMethod;

	@Column
	private float amount;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public OrderYzw getOrder() {
		return order;
	}

	public PayedMethodYzw getPayedMethod() {
		return payedMethod;
	}

	public float getAmount() {
		return amount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrder(OrderYzw order) {
		this.order = order;
	}

	public void setPayedMethod(PayedMethodYzw payedMethod) {
		this.payedMethod = payedMethod;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
