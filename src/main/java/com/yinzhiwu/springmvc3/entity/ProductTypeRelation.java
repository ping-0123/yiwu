package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="product_type_relation",
		uniqueConstraints={@UniqueConstraint(columnNames="productId")})
public class ProductTypeRelation {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer productId;
	
	@ManyToOne
	@JoinColumn(name="product_type_id")
	private ProductType type;	
}
