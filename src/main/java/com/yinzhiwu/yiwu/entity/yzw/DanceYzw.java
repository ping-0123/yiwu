package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vdance")
public class DanceYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7252910249847156886L;

	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(length = 32)
	private String name;

	@Column(length = 32)
	private String danceClass;

	@Column
	private Float remuneration;

	// @OneToMany(mappedBy="dance")
	// List<CourseYzw> courses = new ArrayList<>();

	public DanceYzw() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDanceClass() {
		return danceClass;
	}

	public Float getRemuneration() {
		return remuneration;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDanceClass(String danceClass) {
		this.danceClass = danceClass;
	}

	public void setRemuneration(Float remuneration) {
		this.remuneration = remuneration;
	}

}
