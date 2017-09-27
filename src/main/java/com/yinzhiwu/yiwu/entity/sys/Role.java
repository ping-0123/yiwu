package com.yinzhiwu.yiwu.entity.sys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.yinzhiwu.yiwu.entity.BaseEntity;

@Entity
@Table(name="sys_role", uniqueConstraints={@UniqueConstraint(name="uk_role_name", columnNames={"name"}),
		@UniqueConstraint(name="uk_role_code", columnNames={"code"})
})
@Where(clause="dataStatus <> 2")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7015625301711562788L;
	
	@Size(min=2, max=32)
	@Column(length=32, updatable=false)
	private String code;
	
	@Column(length=32, updatable=false)
	@Size(min=2, max=32)
	private String name;
	
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="sys_role_resource", 
		joinColumns=@JoinColumn(name="role_id", foreignKey=@ForeignKey(name="fk_roleResource_role_id")),
		inverseJoinColumns=@JoinColumn(name="resource_id", foreignKey=@ForeignKey(name="fk_roleResource_resource_id", value=ConstraintMode.NO_CONSTRAINT)))
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Resource> resources = new HashSet<Resource>();
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the resources
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
