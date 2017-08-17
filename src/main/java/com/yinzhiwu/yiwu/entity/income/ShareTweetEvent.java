package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.entity.type.EventType;

@Entity
public class ShareTweetEvent extends IncomeEvent {

	public ShareTweetEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public ShareTweetEvent() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4733655567988278593L;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_incomeEventShareTweet_tweet_id", value = ConstraintMode.NO_CONSTRAINT))
	private Tweet tweet;

	private Short ordinalOfDay;

	public Tweet getTweet() {
		return tweet;
	}

	public Short getOrdinalOfDay() {
		return ordinalOfDay;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	public void setOrdinalOfDay(Short ordinalOfDay) {
		this.ordinalOfDay = ordinalOfDay;
	}

}
