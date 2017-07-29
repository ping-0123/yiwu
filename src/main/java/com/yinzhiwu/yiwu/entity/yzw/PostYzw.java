package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "vpost")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class PostYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7814938629277335258L;

	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer type;

	@Column(length = 50)
	private String name;

	@Column(length = 200)
	private String description;
	
	@Column
	private Boolean removed = Boolean.FALSE;

	@Column
	private Integer flag;

	@Column
	private Integer wparam;

	@Column
	private Integer lparam;
	

	public Integer getId() {
		return id;
	}

	public Integer getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public Integer getFlag() {
		return flag;
	}

	public Integer getWparam() {
		return wparam;
	}

	public Integer getLparam() {
		return lparam;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void setWparam(Integer wparam) {
		this.wparam = wparam;
	}

	public void setLparam(Integer lparam) {
		this.lparam = lparam;
	}

	
	
}
