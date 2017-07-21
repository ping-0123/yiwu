package com.yinzhiwu.yiwu.entity.yzw;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vinterval")
public class IntervalYzw  extends BaseYzwEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2882793635836373337L;

	@Id
	@GeneratedValue
	@Column(length=32)
	private String id;
	
	@Column(length=32)
	private String name;
	
	@Column
	private Time start;
	
	@Column
	private Time end;
	
	@Column
	private float hours;

	public IntervalYzw(){
		super();
	}
	
	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Time getStart() {
		return start;
	}

	public final void setStart(Time start) {
		this.start = start;
	}

	public final Time getEnd() {
		return end;
	}

	public final void setEnd(Time end) {
		this.end = end;
	}

	public final float getHours() {
		return hours;
	}

	public final void setHours(float hours) {
		this.hours = hours;
	}
	
	
}
