package com.yinzhiwu.springmvc3.model;

public class TweetModel {
	
	private String author;
	
	private String title;
	
	private int tweet_type_id;
	
	private String content;

	
	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public int getTweet_type_id() {
		return tweet_type_id;
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

	public void setTweet_type_id(int tweet_type_id) {
		this.tweet_type_id = tweet_type_id;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
