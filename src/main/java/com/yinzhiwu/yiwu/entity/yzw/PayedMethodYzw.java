package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vpayed_method")
public class PayedMethodYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3777295403409968930L;
	public static final PayedMethodYzw INNER_PAYED_METHOD = new PayedMethodYzw(7,"程序内部专用");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 32)
	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public PayedMethodYzw(){}
	public PayedMethodYzw(Integer id, String name){
		this.id = id;
		this.name= name;
	}
}
