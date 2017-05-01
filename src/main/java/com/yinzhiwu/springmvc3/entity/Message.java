package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Message extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4309449622965840107L;

	@ManyToOne
	private Distributer receiver;
	
	@Column(length=10)
	private String status;
	
	private String content;
	
	@ManyToOne
	private MessageType messageType;


	public Distributer getReceiver() {
		return receiver;
	}

	

	public String getStatus() {
		return status;
	}

	public String getContent() {
		return content;
	}

	public void setReceiver(Distributer receiver) {
		this.receiver = receiver;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public MessageType getMessageType() {
		return messageType;
	}


	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	
}
