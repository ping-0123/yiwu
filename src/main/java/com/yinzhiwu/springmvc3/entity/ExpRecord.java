package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class ExpRecord extends AbstractRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5830294915459028999L;

	private  float currentExp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_ExpRecord_shareTweet_id"))
	private ShareTweet shareTweet;

	public float getCurrentExp() {
		return currentExp;
	}

	public void setCurrentExp(float currentExp) { 
		this.currentExp = currentExp;
	}


	public ShareTweet getShareTweet() {
		return shareTweet;
	}

	public void setShareTweet(ShareTweet shareTweet) {
		this.shareTweet = shareTweet;
	}

	public ExpRecord() {
		super();
	}

	public ExpRecord(@NotNull Distributer beneficiaty, Distributer contributor, float contributedValue, RecordType recordType) {
		super(beneficiaty, contributor, contributedValue, recordType);
		this.currentExp = beneficiaty.getExp() + getIncome();
	}
	
	
	
}
