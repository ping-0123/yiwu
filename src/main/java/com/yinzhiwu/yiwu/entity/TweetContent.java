package com.yinzhiwu.yiwu.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "yiwu_tweet_content")
public class TweetContent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117870677974148746L;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", columnDefinition = "MediumBlob")
	private byte[] content;

	@OneToOne(fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
			mappedBy = "tweetContent")
	private Tweet tweet;

	public TweetContent() {
	}

	public TweetContent(byte[] bytes) {
		super.init();
		this.content = bytes;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

}
