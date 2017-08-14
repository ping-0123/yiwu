package com.yinzhiwu.yiwu.entity.type;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "yiwu_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "whichType", length = 32)
@DiscriminatorValue(value = "abstractType")
public abstract class BaseType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7200613894589888689L;

	@Id
	@GeneratedValue(generator = "assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	private Integer id;

	@Column(length = 50)
	private String name;

	public BaseType() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseType(String name) {
		this.name = name;
	}

	public BaseType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public boolean equals(BaseType another) {
		if (another == null)
			return false;
		return this.id ==another.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
