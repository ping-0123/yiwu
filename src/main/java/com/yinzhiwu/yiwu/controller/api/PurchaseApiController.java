package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderForPurchaseShowApiView;
import com.yinzhiwu.yiwu.service.OrderYzwService;

import io.swagger.annotations.ApiOperation;

/**
*@Author ping
*@Time  创建时间:2017年7月26日上午11:39:52
*
*/

@RestController
@RequestMapping(value="/api/purchase")
public class PurchaseApiController  extends BaseController{

	@Autowired OrderYzwService orderSerivice;
	
	@GetMapping(value="/order/list")
	@ApiOperation(value="获取客户的订单列表")
	public YiwuJson<PageBean<OrderForPurchaseShowApiView>> getOrderList(int customerId, boolean isPayed, int pageNo, int pageSize){
		PageBean<OrderForPurchaseShowApiView> orderPage = null;
		if(isPayed)
			orderPage = orderSerivice.findPayedOrderPageByCustomerId(customerId, pageNo, pageSize);
		else
			orderPage = orderSerivice.findUnpayedOrderPageByCustomerId(customerId,pageNo, pageSize);
		return new YiwuJson<>(orderPage);
	}
}
