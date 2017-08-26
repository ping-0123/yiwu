package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;

public interface OrderYzwDao extends IBaseDao<OrderYzw, String> {
	public String find_last_id();

	public boolean isCustomerFirstOrder(OrderYzw order);

	public float get_effective_brockerage_base(OrderYzw order);

	public List<OrderYzw> find_produce_commission_orders() throws DataNotFoundException;

	public List<OrderYzw> findByCustomer(CustomerYzw customer);

	public List<OrderYzw> findByCustomerId(int customerId);

	public List<String> find_contractNos_by_customer_id(int customerId);

	public Contract findCheckedContractByCustomerIdAndSubCourseType(int customerId, SubCourseType subCourseType);

	public OrderYzw findByContractNO(String contractNo) throws YiwuException;

	public List<OrderYzw> findAllLastDayOrders();

	public PageBean<OrderYzw> findPayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize);

	public PageBean<OrderYzw> findUnpayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize);

	public int updateContractWithHoldTimes(String contractNo, int i);

	public Contract findContractByContractNo(String contractNo);

	public int cleanWithHoldTimes();

	public List<PrivateContractApiView> getPrivateContractsByCustomer(Integer customerId);

	Contract findValidContractsByCustomerIdAndSubCourseTypeAndValidStore(int customerId, SubCourseType subCourseType,
			int storeId);

	/**
	 * 
	 * @param customer 客户
	 * @param lesson 课时
	 * @return 客户的一个可以预约该课时的会籍合约,如果该客户有多个会籍合约约满足要求， 则返回最先结束的哪个会籍合约
	 * @throws YiwuException 没有找到满足条件的会籍合约， 则提示可能的原因
	 */
	Contract findCheckableContractOfCustomerAndLesson(CustomerYzw customer, LessonYzw lesson) throws DataNotFoundException;

}
