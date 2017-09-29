package com.yinzhiwu.yiwu.model.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yinzhiwu.yiwu.entity.Message;

public class MessageApiView {

	private int id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private String content;

	private Message.Status status;

	public MessageApiView() {
	};

	public MessageApiView(Message m) {
		this.id = m.getId();
		this.date = m.getCreateTime();
		this.content = m.getContent();
		this.status = m.getStatus();
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public Message.Status getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStatus(Message.Status status) {
		this.status = status;
	}

}
