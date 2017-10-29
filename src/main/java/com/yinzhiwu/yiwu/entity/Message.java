package com.yinzhiwu.yiwu.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.Assert;

@SuppressWarnings("serial")
@Entity
@Table(name = "yiwu_message")
public class Message extends BaseEntity {


	

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_Message_receiver_id"))
	private Distributer receiver;

	private Status status;

	private String content;


	public Message() {
	}

	public Message(Distributer receiver, String content) {
		Assert.notNull(receiver);
		init();
		this.receiver = receiver;
		this.content = content;
	}

	@Override
	public void init() {
		super.init();
		this.status = Status.UNREAD;
	}

	public Distributer getReceiver() {
		return receiver;
	}

	public String getContent() {
		return content;
	}

	public void setReceiver(Distributer receiver) {
		this.receiver = receiver;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		UNREAD(0), READ(1);
		private Status(int index) {
		}
	}
}
