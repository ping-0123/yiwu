package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.OrderAbbrApiView;
import com.yinzhiwu.springmvc3.model.view.OrderApiView;

public interface OrderYzwService extends IBaseService<OrderYzw, String> {

	YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId);

	YiwuJson<OrderApiView> findById(String id);



}
