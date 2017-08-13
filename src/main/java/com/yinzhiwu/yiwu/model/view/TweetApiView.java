package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Tweet;

public class TweetApiView {


	private int id;

	private String title;

	private String author;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date editDate;

	private String contentHtml;

	public TweetApiView() {
	}

	public TweetApiView(Tweet t) {
		this.id = t.getId();
		this.title = t.getTitle();
		this.author = t.getAuthor();
		this.editDate = t.getEditDate();
		this.contentHtml = new String(t.getTweetContent().getContent());
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

}
