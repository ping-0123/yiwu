package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.springmvc3.entity.type.EventType;

@Entity
@Table(name="yiwu_event")
public class Event extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1318050842407338699L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_event_distributer_id"))
	private Distributer distributer;
	
	private Date occurTime;
	
	private Float param;
	
	@ManyToOne()
	@JoinColumn(foreignKey=@ForeignKey(name="fk_event_type_id"))
	private EventType type;
	
	private String comment;

	
	public Distributer getDistributer() {
		return distributer;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public Float getParam() {
		return param;
	}

	public EventType getType() {
		return type;
	}

	public String getComment() {
		return comment;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public void setParam(Float param) {
		this.param = param;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}
