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
import com.yinzhiwu.yiwu.enums.DataStatus;

/**
 * name rule foreign key: fk_{table name}_{foreign key column name} foreign key
 * column name: {parent table name}_{referenced column name} unique key:
 * uk_{table name}_{unique key column name}
 */

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {


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
	@Column(updatable = false, name="createDate")
	private Date createTime;

	@Column(name="lastModifiedUserId")
	private Integer lastChangeUserId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(insertable = true, updatable = true, name="lastModifiedDate")
	protected Date lastChangeTime;
	
	@Column(columnDefinition="TINYINT unsigned not null default 0")
	@Enumerated(value=EnumType.ORDINAL)
	private DataStatus dataStatus;

	public BaseEntity() {
	}

	public void init() {
		Date date = new Date();
		this.createTime = date;
		this.lastChangeTime = date;
		if(dataStatus ==null)
			dataStatus = DataStatus.NORMAL;
		User user = UserContext.getUser();
		if(user != null){
			this.createUserId = user.getId();
			this.lastChangeUserId = user.getId();
		}
	}

	public void beforeUpdate() {
		Date date = new Date();
		this.lastChangeTime = date;
		User user = UserContext.getUser();
		if(user !=null)
			this.lastChangeUserId = user.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastChangeUserId
	 */
	public Integer getLastChangeUserId() {
		return lastChangeUserId;
	}

	/**
	 * @param lastChangeUserId the lastChangeUserId to set
	 */
	public void setLastChangeUserId(Integer lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	/**
	 * @return the lastChangeTime
	 */
	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	/**
	 * @param lastChangeTime the lastChangeTime to set
	 */
	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public DataStatus getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}

}