package com.yinzhiwu.springmvc3.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.ReturnedJson;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.OrderAbbrApiView;
import com.yinzhiwu.springmvc3.model.view.OrderApiView;
import com.yinzhiwu.springmvc3.service.OrderService;
import com.yinzhiwu.springmvc3.service.OrderYzwService;

@RestController
@RequestMapping(value="/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private OrderYzwService orderYzwService;
	
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
	
	@GetMapping(value="/list")
	public YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId){
		return orderYzwService.findByDistributerId(distributerId);
	}
	
	@GetMapping(value="/id/{id}")
	public YiwuJson<OrderApiView> findById(@PathVariable String id){
		return orderYzwService.findById(id);
	}
	
	@PutMapping("/{id}")
	public YiwuJson<Boolean> modify(OrderYzw order, @PathVariable String id){
		try {
			orderYzwService.modify(id, order);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
		if(order.geteContractStatus())
			return new YiwuJson<>("成功确认订单", new Boolean(true));
		return new YiwuJson<>(new Boolean(true));
	}
	
}
