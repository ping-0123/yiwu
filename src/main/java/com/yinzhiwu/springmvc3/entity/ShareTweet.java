package com.yinzhiwu.springmvc3.entity;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="share_style", length=32)
@DiscriminatorValue("Share")
@Table(name="share_tweet")
public class ShareTweet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2386938959605250662L;
	
	private Distributer sharer;
	
	private Tweet tweet;
	
	private Date shareDate;

	@ManyToOne
	public Distributer getSharer() {
		return sharer;
	}

	@ManyToOne
	public Tweet getTweet() {
		return tweet;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setSharer(Distributer sharer) {
		this.sharer = sharer;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}
	
	
}
