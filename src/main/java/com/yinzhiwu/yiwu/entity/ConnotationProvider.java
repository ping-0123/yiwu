package com.yinzhiwu.yiwu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:28:16
*
*/


@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_connotation_provider")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ConnotationProvider extends BaseEntity{
	
	@NotNull
	@Column(length=128)
	private String name;
	
	private String briefIntroduction;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
	
	
}
