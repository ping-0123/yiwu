package com.yinzhiwu.yiwu.entity.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "yiwu_product_type", uniqueConstraints = @UniqueConstraint(name = "uk_ProductType_name", columnNames = "name"))
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// uk
	@Column(name = "name", nullable = false)
	private String name;

	public ProductType() {
	};

	public ProductType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return name;
	}

}
