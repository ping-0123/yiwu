package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.MessageApiView;
import com.yinzhiwu.springmvc3.service.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/list")
	public YiwuJson<List<MessageApiView>> findByReceiverId(int receiverId)
	{
		if(receiverId <=0 )
			return new YiwuJson<>("receiverId 必须大于0");
		return messageService.findByReceiverId(receiverId);
	}
	
	
	@Deprecated
	@GetMapping("/id/{id}")
	public YiwuJson<MessageApiView> findById(@PathVariable int id){
		if(id<=0)
			return new YiwuJson<>("message id must be more than zero");
		return messageService.findById(id);
	}
	
	@GetMapping("/{id}")
	public YiwuJson<MessageApiView> doGet(@PathVariable int id){
		if(id<=0)
			return new YiwuJson<>("message id must be more than zero");
		return messageService.findById(id);
	}
}
