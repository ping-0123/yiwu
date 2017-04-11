package com.yinzhiwu.springmvc3.service;

import java.util.Date;
import java.util.List;

import com.yinzhiwu.springmvc3.model.BriefOrder;

public interface OrderService {

	List<BriefOrder> getDailyOrderByStore(int storeId, Date payedDate);

}
