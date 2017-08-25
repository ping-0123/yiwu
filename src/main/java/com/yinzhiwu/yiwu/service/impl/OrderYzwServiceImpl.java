package com.yinzhiwu.yiwu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw.VipAttributer;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;
import com.yinzhiwu.yiwu.service.ElectricContractYzwService;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderDto;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderSaveDto;

@Service
public class OrderYzwServiceImpl extends BaseServiceImpl<OrderYzw, String> implements OrderYzwService {

	@Autowired
	private OrderYzwDao orderDao;

	@Autowired
	private DistributerDao distributerDao;
	@Autowired private EmployeeYzwDao employeeDao;
	@Autowired private ElectricContractYzwService econtractService;
	@Autowired private ProductYzwDao productDao;
	@Autowired private CustomerYzwDao customerDao;
	@Autowired private DepartmentYzwDao deptDao;
	
	@Autowired
	public void setBaseDao(OrderYzwDao orderYzwDao) {
		super.setBaseDao(orderYzwDao);
	}

	
	@Override
	public YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId) {
		try {
			Distributer distributer = distributerDao.get(distributerId);
			if (distributer == null)
				return new YiwuJson<>("no Distributer found by id :" + distributerId);
			CustomerYzw customer = distributer.getCustomer();
			if (customer == null)
				return new YiwuJson<>("no customer found by distributerId: " + distributerId);
			List<OrderYzw> orders = orderDao.findByCustomer(customer);
			List<OrderAbbrApiView> views = new ArrayList<>();
			for (OrderYzw o : orders) {
				views.add(new OrderAbbrApiView(o));
			}
			return new YiwuJson<>(views);
		} catch (Exception e) {
			logger.debug(e);
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<OrderApiView> findById(String id) {
		try {
			OrderYzw order = orderDao.get(id);
			if (order == null)
				return new YiwuJson<>("no order found by id: " + id);
			return new YiwuJson<>(new OrderApiView(order));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public void modify(String id, OrderYzw entity)
			throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		OrderYzw source = super.get(id);
		if (source == null)
			throw new DataNotFoundException("id=" + id + " 的订单不存在");
		if (/** !source.geteContractStatus() && */
		entity.geteContractStatus()) {
			if(null == entity.getCourse()){
				CourseYzw course = new CourseYzw();
				course.setId("");
				entity.setCourse(course);
			}
			if (entity.getContract() == null) {
				Contract con = new Contract();
				entity.setContract(con);
			}
			if (source.getDiscount() < 1)
				entity.getContract().setStatus(ContractStatus.UN_CHECKED);
			else
				entity.getContract().setStatus(ContractStatus.CHECKED);
		}
		super.modify(source, entity);
	}



	@Override
	public PageBean<OrderDto> findPageByCustomer(int customerId, boolean isPayed, int pageNo,
			int pageSize) {
		
		PageBean<OrderYzw> orderPage = null;
		if(isPayed)
			orderPage = orderDao.findPayedOrderPageByCustomerId(customerId, pageNo, pageSize);
		else
			orderPage = orderDao.findUnpayedOrderPageByCustomerId(customerId,  pageNo, pageSize);
		
		//空值检查
		if(orderPage == null )
			return null;
		if(orderPage.getData() == null || orderPage.getData().size() ==0)
			return null;
		
		List<OrderDto> views = new ArrayList<>();
		for (OrderYzw order : orderPage.getList()) {
			views.add(_wrapToView(order));
		}
		PageBean<OrderDto> viewPage = 
				new PageBean<>(orderPage.getPageSize(), orderPage.getCurrentPage(), orderPage.getTotalRecord(), views);
			
		return viewPage;
	}

	private OrderDto _wrapToView(OrderYzw order) {
		OrderDto view = OrderDto.fromOrder(order);
		EmployeeYzw employee = employeeDao.get(view.getSalesmanId());
		if(employee != null)
			view.setSalesmanName(employee.getName());
		return view;
	}

	@Override
	public String save(OrderYzw order){
		orderDao.save(order);
		//保存合同
		ElectricContractYzw econtract = new ElectricContractYzw(order);
		econtractService.save(econtract);
		//修改客户资料
		/**
		 * 
		 
		EmployeeYzw salesman = employeeDao.get(order.getCreateUserId());
		CustomerYzw customer = order.getCustomer();
		customer.setSalesman(salesman);
		customerDao.update(customer);
		*/
		//修改distributer资料
		/**
		 * 
		Distributer distributer = distributerDao.findByCustomerId(order.getCustomer().getId());
		if(distributer == null)
			throw new RuntimeException("该用户尚未注册到E5系统");
		distributer.setFollowedByStore(order.getStore());
		distributer.setServer(salesman);
		distributerDao.update(distributer);
		 */
		return order.getId();
	}

	@Override
	public String save(OrderSaveDto orderSaveDto) throws Exception {
		OrderYzw order = _wrapToOrder(orderSaveDto);
		return this.save(order);
	}

	private OrderYzw _wrapToOrder(OrderSaveDto dto) throws Exception {
		OrderYzw order = new OrderYzw();
		ProductYzw product = productDao.get(dto.getProductId());
		if(product == null ) throw new Exception("无效的productId" +dto.getProductId());
		CustomerYzw customer = customerDao.get(dto.getCustomerId());
		if(customer == null) throw new Exception("无效的客户Id: " + dto.getCustomerId());
		DepartmentYzw store = deptDao.get(dto.getStoreId());
		if(store == null ) throw new Exception("无效的门店Id: " + dto.getStoreId());
		
		order.setMemberCardNo(customer.getMemberCard());
		order.setProduct(product);
		order.setMarkedPrice((float)product.getMarkedPrice());
		order.setCount(dto.getQuantity());
		order.setPayedAmount(dto.getPayedAmount());
		order.setDiscount(order.getPayedAmount()/(order.getMarkedPrice()* order.getCount()));
		order.setCustomer(customer);
		if(customer.getCustomerAgeType() == null)
			customer.setCustomerAgeType(dto.getAgeType());
		Date date = new Date();
		order.setPayedDate(dto.getPayedDate());
		if(order.getPayedDate() == null)
			order.setPayedDate(date);
		if(orderDao.findCountByProperty("customer.id", customer.getId()) > 0)
			order.setVipAttr(VipAttributer.RENEW_MEMBER);
		else
			order.setVipAttr(VipAttributer.NEW_MEMBER);
		order.setStore(store);
		//合约信息
		Contract contract = new Contract();
		contract.setStatus(ContractStatus.UN_PAYED);
		if(dto.getUsefulTimes() == null){
			contract.setValidityTimes(product.getUsefulTimes());
			contract.setRemainTimes(BigDecimal.valueOf(product.getUsefulTimes()));
		}else{
			contract.setValidityTimes(dto.getUsefulTimes());
			contract.setRemainTimes(BigDecimal.valueOf(dto.getUsefulTimes()));
		}
		if(dto.getContractStartDate() == null)
			contract.setStart(date);
		else
			contract.setStart(dto.getContractStartDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(contract.getStart());
		calendar.add(Calendar.MONTH, product.getUsefulLife());
		contract.setEnd(calendar.getTime());
		if(product.getContractType() != null)
			contract.setType(product.getContractType().getContractType());
		contract.setSubType(dto.getMiddleProductType());
		contract.setValidStoreIds(dto.getValidStoreIds());
		order.setContract(contract);
		return order;
	}

	@Override
	public void modify(String id, OrderSaveDto orderDto) throws Exception {
		OrderYzw source = orderDao.get(id);
		if( modifiable(source)){
			OrderYzw order = _wrapToOrder(orderDto);
			super.modify(source, order);
		}
	}

	private boolean modifiable(OrderYzw source) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source.getCreateTime());
		Calendar currentCalendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, OrderYzw.DELETABLE_AFTER_HOURS);
		if(calendar.compareTo(currentCalendar)<0){
			throw new Exception("24小时之前生成的订单不能修改删除");
		}
		if(ContractStatus.UN_PAYED != source.getContract().getStatus())
			throw new Exception("已支付订单不能删除");
		return true;
	}


	@Override
	public YiwuJson<List<PrivateContractApiView>> getPrivateContractsByCustomer(Integer customerId) {
		List<PrivateContractApiView> views = orderDao.getPrivateContractsByCustomer(customerId);
		if(views.size() ==0)
			return YiwuJson.createByErrorMessage("您没有购买过私教课产品");
		return YiwuJson.createBySuccess(views);
	}
	
	
}
