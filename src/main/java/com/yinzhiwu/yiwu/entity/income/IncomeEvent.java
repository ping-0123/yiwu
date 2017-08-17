package com.yinzhiwu.yiwu.entity.income;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinzhiwu.yiwu.entity.BaseEntity;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class IncomeEvent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1318050842407338699L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(value=ConstraintMode.NO_CONSTRAINT))
	private Distributer distributer;

	private Date occurTime;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private EventType type;

	private Float param;

	@JsonIgnore
	@OneToMany(mappedBy = "incomeEvent")
	private List<IncomeRecord> incomeRecords = new ArrayList<>();

	public IncomeEvent() {
	};

	public IncomeEvent(Distributer distributer, EventType type, Float param) {
		init();
		this.distributer = distributer;
		this.type = type;
		this.param = param;
	}

	@Override
	public void init() {
		super.init();
		this.occurTime = super.getCreateDate();
	}

	public IncomeRecord getAppointedIncomeRecord(IncomeType type) {
		if (this.incomeRecords == null || this.incomeRecords.size() == 0)
			return null;
		for (IncomeRecord incomeRecord : incomeRecords) {
			if (IncomeType.EXP.equals(incomeRecord.getIncomeType()))
				return incomeRecord;
		}
		return null;
	}

	public Message generateMessage(IncomeRecord incomeRecord) {
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

	public List<IncomeRecord> getIncomeRecords() {
		return incomeRecords;
	}

	public void setIncomeRecords(List<IncomeRecord> incomeRecords) {
		this.incomeRecords = incomeRecords;
	}

}
