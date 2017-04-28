package com.yinzhiwu.springmvc3.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("messageType")
public class MessageType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807718393731720615L;
	
	private List<Message> messages = new ArrayList<>();

	public MessageType() {
		super();
	}

	@OneToMany(mappedBy="messageType")
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	

}
