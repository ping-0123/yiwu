package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.MessageApiView;
import com.yinzhiwu.yiwu.service.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageApiController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/list")
	public YiwuJson<List<MessageApiView>> findByReceiverId(int receiverId) {
		if (receiverId <= 0)
			return new YiwuJson<>("receiverId 必须大于0");
		return messageService.findByReceiverId(receiverId);
	}

	@GetMapping("/{id}")
	public YiwuJson<MessageApiView> doGet(@PathVariable int id) {
		if (id <= 0)
			return new YiwuJson<>("message id must be more than zero");
		return messageService.findById(id);
	}
}
