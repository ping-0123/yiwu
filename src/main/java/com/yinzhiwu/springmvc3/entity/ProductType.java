package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="product_type", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class ProductType {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;

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
	
	
}
