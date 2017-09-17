package com.yinzhiwu.yiwu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.enums.DataStatus;

/**
 * name rule foreign key: fk_{table name}_{foreign key column name} foreign key
 * column name: {parent table name}_{referenced column name} unique key:
 * uk_{table name}_{unique key column name}
 */

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(updatable = false)
	private Integer createUserId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(updatable = false)
	private Date createDate;

	private Integer lastModifiedUserId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(insertable = true, updatable = true)
	protected Date lastModifiedDate;
	
	@Enumerated(value=EnumType.ORDINAL)
	private DataStatus dataStatus = DataStatus.NORMAL;

	public BaseEntity() {
	}

	public void init() {
		Date date = new Date();
		this.createDate = date;
		this.lastModifiedDate = date;
		User user = UserContext.getUser();
		if(user != null){
			this.createUserId = user.getId();
			this.lastModifiedUserId = user.getId();
		}
	}

	public void beforeUpdate() {
		Date date = new Date();
		this.lastModifiedDate = date;
		User user = UserContext.getUser();
		if(user !=null)
			this.lastModifiedUserId = user.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date modifyDate) {
		this.lastModifiedDate = modifyDate;
	}

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass().getPackage() != obj.getClass().getPackage()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		return true;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getLastModifiedUserId() {
		return lastModifiedUserId;
	}

	public void setLastModifiedUserId(int moddifiedUserId) {
		this.lastModifiedUserId = moddifiedUserId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public void setLastModifiedUserId(Integer lastModifiedUserId) {
		this.lastModifiedUserId = lastModifiedUserId;
	}

	public DataStatus getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}

}