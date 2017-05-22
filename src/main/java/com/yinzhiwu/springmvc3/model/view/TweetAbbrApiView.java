package com.yinzhiwu.springmvc3.model.view;

import com.yinzhiwu.springmvc3.entity.Tweet;

public class TweetAbbrApiView {
	
	private int tweetId;
	
	private String coverIconUrl;
	
	private String title;
	
	private String digest;
	
	private String tweetTypeName;
	
	public TweetAbbrApiView(){
	};
	
	public TweetAbbrApiView(Tweet t){
		this.tweetId = t.getId();
		this.coverIconUrl = t.getCoverIconUrl();
		this.title =t.getTitle();
		this.digest = t.getDigest();
		this.tweetTypeName = t.getTweetType().getName();
	}

	public int getTweetId() {
		return tweetId;
	}

	public String getCoverIconUrl() {
		return coverIconUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getDigest() {
		return digest;
	}

	public String getTweetTypeName() {
		return tweetTypeName;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public void setCoverIconUrl(String coverIconUrl) {
		this.coverIconUrl = coverIconUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setTweetTypeName(String tweetTypeName) {
		this.tweetTypeName = tweetTypeName;
	}
	
	
}
