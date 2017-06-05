package com.yinzhiwu.springmvc3.service;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.model.view.OrderApiView;
import com.yinzhiwu.springmvc3.model.view.OrderOldApiView;

public interface OrderService {

	List<OrderOldApiView> getDailyOrderByStore(int storeId, Date payedDate);

	List<OrderOldApiView> getDailyOrderByStore(int storeId, Date payedDate, int productTypeId);

}
