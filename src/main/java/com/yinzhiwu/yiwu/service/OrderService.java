package com.yinzhiwu.yiwu.service;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.yiwu.model.view.OrderOldApiView;

public interface OrderService {

	List<OrderOldApiView> getDailyOrderByStore(int storeId, Date payedDate);

	List<OrderOldApiView> getDailyOrderByStore(int storeId, Date payedDate, int productTypeId);

}
