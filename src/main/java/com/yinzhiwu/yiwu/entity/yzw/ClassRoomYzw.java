package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "vclassroom")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class ClassRoomYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5491370511452100257L;

	@Id
	@Column(length = 32, name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(length = 32)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_Id", foreignKey = @ForeignKey(name = "fk_classRoom_store_id", value = ConstraintMode.NO_CONSTRAINT))
	private DepartmentYzw store;

	@Column(length = 32)
	private String storeName;

	@Column
	private Integer maxStudentCount;

	@Column
	private Integer minStudentCount;

	public ClassRoomYzw() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DepartmentYzw getStore() {
		return store;
	}

	public String getStoreName() {
		return storeName;
	}

	public Integer getMaxStudentCount() {
		return maxStudentCount;
	}

	public Integer getMinStudentCount() {
		return minStudentCount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStore(DepartmentYzw store) {
		this.store = store;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setMaxStudentCount(Integer maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}

	public void setMinStudentCount(Integer minStudentCount) {
		this.minStudentCount = minStudentCount;
	}

}
