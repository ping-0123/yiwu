package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.service.OrderYzwService;

@Service
public class OrderYzwServiceImpl  extends BaseServiceImpl<OrderYzw,String> implements OrderYzwService{
	
	@Autowired
	private OrderYzwDao orderDao;
	
	@OneToMany
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	public void setBaseDao(OrderYzwDao orderYzwDao){
		super.setBaseDao(orderYzwDao);
	}

	@Override
	public YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId) {
		try{
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
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<OrderApiView> findById(String id) {
		try{
			OrderYzw order = orderDao.get(id);
			if(order == null)
				return new YiwuJson<>("no order found by id: " + id);
			return new YiwuJson<>(new OrderApiView(order));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public void modify(String id, OrderYzw entity)
			throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		OrderYzw source = super.get(id);
		if(source == null ) throw new DataNotFoundException("id=" + id +  " 的订单不存在");
		if(/**!source.geteContractStatus() && */entity.geteContractStatus())
		{
			if(entity.getContract() == null){
				Contract con = new Contract();
				entity.setContract(con);
			}
			if(source.getDiscount() < 1)
				entity.getContract().setStatus(ContractStatus.UN_CHECKED);
			else
				entity.getContract().setStatus(ContractStatus.CHECKED);
		}
		super.modify(source, entity);
	}


	
}