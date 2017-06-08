package com.yinzhiwu.springmvc3.entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yinzhiwu.springmvc3.entity.type.RecordType;


@Entity
@DiscriminatorValue("WithDrawRecord")
public class WithDrawRecord extends BrokerageRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8070091039216201578L;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="capitalAccount_id",foreignKey=
		@ForeignKey(name="fk_withDrawRecord_capitalAccount_id"))
	private CapitalAccount account;
	
	
	
	public CapitalAccount getAccount() {
		return account;
	}

	public void setAccount(CapitalAccount account) {
		this.account = account;
	}

	public WithDrawRecord() {
		super();
	}

	
	public WithDrawRecord(Distributer beneficiaty, Distributer contributor, float contributedValue,
			RecordType recordType) {
		super(beneficiaty, contributor, contributedValue, recordType);
	}
	
}
