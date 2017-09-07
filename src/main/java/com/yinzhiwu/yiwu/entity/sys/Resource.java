package com.yinzhiwu.yiwu.entity.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yinzhiwu.yiwu.entity.BaseEntity;

/**
*@Author ping
*@Time  创建时间:2017年8月29日下午4:07:18
*
*/

@Entity
@Table(name="sys_resource")
public class Resource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1565342126871220575L;
	public enum ResourceType{
		MENU,
		BUTTON
	}
	
	@Column(length=32)
	private String code;
	
	@Column(length=32)
	private String name;
	
	@Column(length=128)
	private String url;
	
	@Column(length=128)
	private String permission;
	
	private ResourceType type;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private Resource parent;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	private List<Resource> children = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getPermission() {
		return permission;
	}

	public ResourceType getType() {
		return type;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}


	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}
	
	
}
