package com.yinzhiwu.yiwu.entity.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.BaseEntity;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午3:32:19
*
*/

@Entity
@Table(name="sys_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7015625301711562788L;
	
	@Column(length=32)
	private String code;
	@Column(length=32)
	private String name;
	@Column
	private String description;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(name="sys_role_resource", 
		joinColumns=@JoinColumn(name="role_id", foreignKey=@ForeignKey(name="fk_roleResource_role_id")), 
		inverseJoinColumns=@JoinColumn(name="resource_id", foreignKey=@ForeignKey(name="fk_roleResource_resource_id")))
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private List<Resource> resources = new ArrayList<>();
	
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	
}
