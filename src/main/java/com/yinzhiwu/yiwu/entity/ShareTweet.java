package com.yinzhiwu.yiwu.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*@Author ping
*@Time  创建时间:2017年11月9日下午4:51:49
*
*/

@SuppressWarnings("serial")
@Entity
@Table(name="yiwu_share_tweet")
public class ShareTweet extends BaseEntity{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_shareTweet_distributer_id"))
	private Distributer distributer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_shareTweet_tweet_id"))
	private Tweet tweet;
	
	private Date date;
	
	//当天第几次分享
	private Integer ordinalNo;

	@Override
	public void init() {
		super.init();
		date = super.getCreateTime();
	}
	
	public Distributer getDistributer() {
		return distributer;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setDistributer(Distributer distributer) {
		this.distributer = distributer;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}	
