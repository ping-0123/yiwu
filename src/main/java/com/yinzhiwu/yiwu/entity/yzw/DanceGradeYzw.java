package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
*@Author ping
*@Time  创建时间:2017年10月25日上午11:39:53
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="vdance_grade")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class DanceGradeYzw extends BaseYzwEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	
	
}
