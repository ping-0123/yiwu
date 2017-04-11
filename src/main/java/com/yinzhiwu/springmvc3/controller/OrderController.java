package com.yinzhiwu.springmvc3.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.service.OrderService;

@Controller
@RequestMapping(value="/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value="/getDailyOrders")
	public ReturnedJson getDailyOrdersByStore(@RequestParam int storeId,
											@RequestParam Date payedDate){
		return new ReturnedJson(
				orderService.getDailyOrderByStore(storeId, payedDate));
	}
}
