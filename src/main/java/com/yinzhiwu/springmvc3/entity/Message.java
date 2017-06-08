package com.yinzhiwu.springmvc3.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yinzhiwu.springmvc3.entity.type.MessageType;

@Entity
@Table(name="yiwu_message")
public class Message extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4309449622965840107L;
	
	public enum Status{
		UNREAD,
		READ
	}

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_Message_receiver_id"))
	private Distributer receiver;
	
	private Status status;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_Message_messageType_id"))
	private MessageType messageType;


	
	
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



	public MessageType getMessageType() {
		return messageType;
	}


	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}




	public Status getStatus() {
		return status;
	}




	public void setStatus(Status status) {
		this.status = status;
	}




	public Message() {
		super();
		this.status = Status.UNREAD;
	}
	
	
}
