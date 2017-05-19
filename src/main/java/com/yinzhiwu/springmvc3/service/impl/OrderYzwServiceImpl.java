package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.OrderAbbrApiView;
import com.yinzhiwu.springmvc3.model.view.OrderApiView;
import com.yinzhiwu.springmvc3.service.OrderYzwService;

@Service
public class OrderYzwServiceImpl  extends BaseServiceImpl<OrderYzw,String> implements OrderYzwService{
	
	@Autowired
	private OrderYzwDao orderDao;
	
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	public void setBaseDao(OrderYzwDao orderYzwDao){
		super.setBaseDao(orderYzwDao);
	}

	@Override
	public YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId) {
		Distributer distributer = distributerDao.get(distributerId);
		if(distributer == null)
			return new YiwuJson<>("no Distributer found by id :" + distributerId);
		CustomerYzw customer = distributer.getCustomer();
		if(customer == null )
			return new YiwuJson<>("no customer found by distributerId: " + distributerId);
		List<OrderYzw> orders = orderDao.findByCustomer(customer);
		List<OrderAbbrApiView> views = new ArrayList<>();
		for (OrderYzw o : orders) {
			views.add(new OrderAbbrApiView(o));
		}
		return new YiwuJson<>(views);
	}

	@Override
	public YiwuJson<OrderApiView> findById(String id) {
		OrderYzw order = orderDao.get(id);
		if(order == null)
			return new YiwuJson<>("no order found by id: " + id);
		return new YiwuJson<>(new OrderApiView(order));
	}

}
