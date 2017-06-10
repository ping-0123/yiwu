package com.yinzhiwu.springmvc3.entity.income;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.springmvc3.entity.BaseEntity;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.entity.type.EventType;

@Entity
@Table(name="yiwu_income_event")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="event_type")
@DiscriminatorValue("IncomeEvent")
public class IncomeEvent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1318050842407338699L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_event_distributer_id"))
	private Distributer distributer;
	
	private Date occurTime;
	
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_event_type_id"))
	private EventType type;
	
	private Float param;
	
	public IncomeEvent(){};
	
	public IncomeEvent(Distributer distributer, EventType type, Float param) {
		init();
		this.distributer = distributer;
		this.type = type;
		this.param = param;
	}
	
	@Override
	public void init(){
		super.init();
		this.occurTime = super.getCreateDate();
	}
	
	public Message generateMessage(IncomeRecord incomeRecord){
		return null;
	}
	
	public Distributer getDistributer() {
		return distributer;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public EventType getType() {
		return type;
	}


	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public void setType(EventType type) {
		this.type = type;
	}


	public Float getParam() {
		return param;
	}

	public void setParam(Float param) {
		this.param = param;
	}

	


}
