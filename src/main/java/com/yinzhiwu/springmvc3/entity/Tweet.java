package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", length=32)
@DiscriminatorValue("tweet")
public class Tweet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058523074677494452L;

	private String title;
	
	private String author;
	
	private Content content;
	
	
	private List<ShareTweet> shareTweets = new ArrayList<>();

	@OneToMany(mappedBy="tweet")
	public List<ShareTweet> getShareTweets() {
		return shareTweets;
	}

	public void setShareTweets(List<ShareTweet> shareTweets) {
		this.shareTweets = shareTweets;
	}

	@Column(length=50)
	public String getTitle() {
		return title;
	}

	@Column(length=32)
	public String getAuthor() {
		return author;
	}

	public Content getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(Content content) {
		this.content = content;
	}
}
