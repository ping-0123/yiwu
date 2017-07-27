package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderDto;

public interface OrderYzwService extends IBaseService<OrderYzw, String> {

	YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId);

	YiwuJson<OrderApiView> findById(String id);

	PageBean<OrderDto> findPayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize);

	PageBean<OrderDto> findUnpayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize);

}
