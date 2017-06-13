package com.yinzhiwu.springmvc3.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.model.view.OrderOldApiView;
import com.yinzhiwu.springmvc3.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<OrderOldApiView> getDailyOrderByStore(int storeId, Date payedDate, int productTypeId){
		
		return orderDao.findDailyOrderByStore(storeId, payedDate, productTypeId);
	}

	@Override
	public List<OrderOldApiView> getDailyOrderByStore(int storeId, Date payedDate) {
		return orderDao.findDailyOrderByStore(storeId, payedDate);
	}

}
