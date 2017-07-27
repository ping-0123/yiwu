package com.yinzhiwu.yiwu.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.entity.TweetContent;
import com.yinzhiwu.yiwu.entity.type.TweetType;

public class TweetModel {

	@Size(min = 2, max = 10)
	private String author;

	@Length(min = 3)
	private String title;

	@Min(1)
	private int tweetTypeId;

	private Date editDate;
	
	@Size(min=3, max = 250)
	private String digest;

	@NotNull
	private MultipartFile coverIcon;

	private String coverImageName;
	
	private String coverIconUrl;

	@NotNull
	private String content;

	public Tweet toTweet(){
		Tweet tweet = new Tweet();
		tweet.setAuthor(this.author);
		tweet.setTitle(this.title);
		TweetType type = new TweetType();
		type.setId(this.getTweetTypeId());
		tweet.setTweetType(type);
		tweet.setEditDate(this.editDate);
		tweet.setDigest(this.digest);
		tweet.setCoverImage(this.coverImageName);
		TweetContent con = new TweetContent();
		con.setContent(this.content.getBytes());
		tweet.setTweetContent(con);
		return tweet;
	}
	
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

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getCoverImageName() {
		return coverImageName;
	}

	public void setCoverImageName(String fileName) {
		this.coverImageName = fileName;
	}

}
