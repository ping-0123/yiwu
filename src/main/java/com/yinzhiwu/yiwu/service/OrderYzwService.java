package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;

public interface OrderYzwService extends IBaseService<OrderYzw, String> {

	YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId);

	YiwuJson<OrderApiView> findById(String id);
	
	

}
