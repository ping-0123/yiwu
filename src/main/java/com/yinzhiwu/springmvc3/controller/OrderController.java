package com.yinzhiwu.springmvc3.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.OrderAbbrApiView;
import com.yinzhiwu.springmvc3.model.view.OrderApiView;
import com.yinzhiwu.springmvc3.service.OrderService;
import com.yinzhiwu.springmvc3.service.OrderYzwService;

@Controller
@RequestMapping(value="/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private OrderYzwService orderYzwService;
	
	@ResponseBody
	@RequestMapping(value="/getDailyOrders")
	public ReturnedJson getDailyOrdersByStore(@RequestParam int storeId,
											@RequestParam Date payedDate,
											@RequestParam int productTypeId){
		if (productTypeId==0){
			return new ReturnedJson(
					orderService.getDailyOrderByStore(storeId, payedDate));	
		}else{
			return new ReturnedJson(
					orderService.getDailyOrderByStore(storeId, payedDate, productTypeId));
		}
	}
	
	
	@ResponseBody
	@GetMapping(value="/list")
	public YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId){
		return orderYzwService.findByDistributerId(distributerId);
	}
	
	@ResponseBody
	@GetMapping(value="/id/{id}")
	public YiwuJson<OrderApiView> findById(@PathVariable String id){
		return orderYzwService.findById(id);
	}
	
}
