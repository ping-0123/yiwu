package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.model.ReturnedJson;
import com.yinzhiwu.yiwu.service.CustomerService;

@Controller
@RequestMapping("/api/customer")
public class CustomerApiController {

	@Autowired
	private CustomerService customerServce;

	@ResponseBody
	@RequestMapping(value = "/get", method = { RequestMethod.GET })
	public ReturnedJson getByWeChatNo(@RequestParam String weChatNo) {
		return new ReturnedJson(customerServce.findByWeChat(weChatNo));
	}
}
