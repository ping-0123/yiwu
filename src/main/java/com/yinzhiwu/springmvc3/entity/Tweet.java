package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", length=32)
@DiscriminatorValue("tweet")
public class Tweet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058523074677494452L;

	@Column(length=50)
	private String title;
	
	@Column(length=32)
	private String author;
	
	@Column(length=50)
	private String digest; //信息摘要
	
	@Column(length=50)
	private String coverIconUrl;
	
	private Date editDate;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_Tweet_tweetContent_id"))
	private TweetContent tweetContent;
	
	
	@ManyToOne
	@JoinColumn(name="tweetType_id", foreignKey=@ForeignKey(name="fk_tweet_tweetType_id"))
	private TweetType tweetType;
  
	@OneToMany(mappedBy="tweet")
	private List<ShareTweet> shareTweets = new ArrayList<>();
	
	public List<ShareTweet> getShareTweets() { 
		return shareTweets;
	}

	public Tweet(){
		super();
		this.editDate=super.getCreateDate();
	}
	
	public void setShareTweets(List<ShareTweet> shareTweets) {
		this.shareTweets = shareTweets;
	}


	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return author;
	}



	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public TweetContent getTweetContent() {
		return tweetContent;
	}

	public TweetType getTweetType() {
		return tweetType;
	}

	public void setTweetContent(TweetContent tweetContent) {
		this.tweetContent = tweetContent;
	}

	public void setTweetType(TweetType tweetType) {
		this.tweetType = tweetType;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getDigest() {
		return digest;
	}

	public String getCoverIconUrl() {
		return coverIconUrl;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setCoverIconUrl(String coverIconUrl) {
		this.coverIconUrl = coverIconUrl;
	}




}
