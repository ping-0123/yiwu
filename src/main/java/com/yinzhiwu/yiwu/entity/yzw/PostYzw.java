package com.yinzhiwu.yiwu.entity.yzw;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.yinzhiwu.yiwu.entity.sys.Role;

@Entity
@Table(name = "vpost")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Where(clause="dataStatus <> 2")
public class PostYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7814938629277335258L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer type=1;

	@Column(length = 50, updatable=false)
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
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="sys_post_role",
			joinColumns=@JoinColumn(name="post_id",foreignKey=@ForeignKey( name="fk_postRole_post_id", value=ConstraintMode.NO_CONSTRAINT)),
			inverseJoinColumns=@JoinColumn(name="role_id", foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT, name="fk_postRole_role_id")))
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> roles = new HashSet<>();	
	
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

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
}
