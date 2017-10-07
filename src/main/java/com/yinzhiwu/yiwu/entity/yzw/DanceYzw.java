package com.yinzhiwu.yiwu.entity.yzw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "vdance")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)  //TODO 全缓存 永久有效
public class DanceYzw extends BaseYzwEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7252910249847156886L;

	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name="danceIdGenerator", strategy="assigned")
	private String id;

	@Column(length = 32)
	private String name;

	@Column(length = 32)
	private String danceClass;

	@Column
	private Float remuneration;

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
