package com.yinzhiwu.springmvc3.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class TweetModel {
	
	@Size(min=2,max=10)
	private String author;
							
	@Length(min=3)
	private String title;
	
	@Min(1)
	private int tweetTypeId;
	
	@NotNull
	private String content;

	
	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}


	public String getContent() {
		return content;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public void setContent(String content) {
		this.content = content;
	}

	public int getTweetTypeId() {
		return tweetTypeId;
	}

	public void setTweetTypeId(int tweetTypeId) {
		this.tweetTypeId = tweetTypeId;
	}
	
	
}
