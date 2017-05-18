package com.yinzhiwu.springmvc3.entity;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


@Entity
public class TweetContent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1117870677974148746L;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="content", columnDefinition="Blob")
	private byte[] content;
	
	@OneToOne(fetch=FetchType.LAZY,
			cascade=CascadeType.PERSIST,
			mappedBy="tweetContent")
	private Tweet tweet;
	
	public TweetContent(){
		super();
	}

	public TweetContent(byte[] bytes) {
		this.content = bytes;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	
	
	
}
