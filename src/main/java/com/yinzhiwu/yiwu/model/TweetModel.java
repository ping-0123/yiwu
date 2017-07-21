package com.yinzhiwu.yiwu.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class TweetModel {
	
	@Size(min=2,max=10)
	private String author;
							
	@Length(min=3)
	private String title;
	
	
	@Min(1)
	private int tweetTypeId;
	
	@Size(max=250)
	private String digest;
	
	@NotNull
	private MultipartFile coverIcon;
	
	private String coverIconUrl;
	
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

	public String getDigest() {
		return digest;
	}

	public MultipartFile getCoverIcon() {
		return coverIcon;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setCoverIcon(MultipartFile coverIcon) {
		this.coverIcon = coverIcon;
	}

	public String getCoverIconUrl() {
		return coverIconUrl;
	}

	public void setCoverIconUrl(String coverIconUrl) {
		this.coverIconUrl = coverIconUrl;
	}
	
	
}
