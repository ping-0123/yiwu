package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "yiwu_product_type_relation", uniqueConstraints = {
		@UniqueConstraint(name = "uk_ProductTypeRelation_productId", columnNames = "productId") })
public class ProductTypeRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer productId;

	@ManyToOne
	@JoinColumn(name = "product_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ProductTypeRelation_ProductType_id"))
	private ProductType type;

	public Integer getId() {
		return id;
	}

	public Integer getProductId() {
		return productId;
	}

	public ProductType getType() {
		return type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

}
