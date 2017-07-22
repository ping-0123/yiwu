package com.yinzhiwu.yiwu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.ReturnedJson;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.service.OrderService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

@RestController
@RequestMapping(value="/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private OrderYzwService orderYzwService;
	
	@RequestMapping(value="/getDailyOrders",method={RequestMethod.GET, RequestMethod.POST})
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
	
	@GetMapping(value="/count")
	public YiwuJson<Integer> findCount(int customerId){
		try{
			int count = orderYzwService.findCountByProperty("customer.id", customerId);
			return new YiwuJson<>(new Integer(count));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping(value="/{id}")
	public YiwuJson<OrderApiView> findById(@PathVariable String id){
		return orderYzwService.findById(id);
	}
	
	@PutMapping("/{id}")
	public YiwuJson<Boolean> modify(OrderYzw order, @PathVariable String id){
//		if(order.geteContractStatus()){
//			Contract contract = new Contract();
//			contract.setStatus("已确认");
//			order.setContract(contract);
//		}
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
