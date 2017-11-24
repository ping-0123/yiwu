package com.yinzhiwu.yiwu.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "yiwu_tweet")
public class Tweet extends BaseEntity {

	@Column(length = 50)
	private String title;

	@Column(length = 32)
	private String author;

	@Column
	private String digest; // 信息摘要
	
	@Column(length=128)
	private String coverImage;

	private Date editDate;

	@Column(length=32)
	@Enumerated(EnumType.STRING)
	private com.yinzhiwu.yiwu.enums.TweetType type;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", columnDefinition = "MediumBlob")
	private byte[] content;
	
	@Override
	public void init() {
		super.init();
		if(null == this.editDate)
			this.editDate = super.getCreateTime();
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

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getDigest() {
		return digest;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setCoverImage(String coverIconUrl) {
		this.coverImage = coverIconUrl;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public com.yinzhiwu.yiwu.enums.TweetType getType() {
		return type;
	}

	public void setType(com.yinzhiwu.yiwu.enums.TweetType type) {
		this.type = type;
	}

}
