package com.yinzhiwu.springmvc3.entity.yzwOld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vdance_grade")
public class DanceGrade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=32)
	private String name;

	public DanceGrade() {
	}

	public DanceGrade(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	
	
}
