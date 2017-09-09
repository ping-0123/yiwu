package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderDto;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderSaveDto;

public interface OrderYzwService extends IBaseService<OrderYzw, String> {

	YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId);

	YiwuJson<OrderApiView> findById(String id);


	String save(OrderSaveDto order) throws Exception;

	PageBean<OrderDto> findPageByCustomer(int customerId, boolean isPayed, int pageNo, int pageSize);

	void modify(String id, OrderSaveDto order) throws Exception;

	YiwuJson<List<PrivateContractApiView>> getPrivateContractsByCustomer(Integer customerId);

	YiwuJson<PageBean<OrderApiView>> findPageOfOrderApiViewByDistributer(Distributer distributer, int pageNo, int pageSize);

	void settleContract(String contractNo);

	List<OrderYzw> findBrokeragableOrdersSinceRegesterDate(Distributer distributer);


}
