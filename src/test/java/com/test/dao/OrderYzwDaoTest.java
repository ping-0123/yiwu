package com.test.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.util.ReflectUtils;

/**
*@Author ping
*@Time  创建时间:2017年8月23日下午2:17:15
*
*/

public class OrderYzwDaoTest  extends BaseSpringTest{

	@Autowired
	private OrderYzwDao orderYzwDao;
	@Autowired private EmployeeYzwDao employeeDao;
	
	@Autowired private DistributerDao distributerDao;
	
	@Test
	public void testFindByExample2(){
		Distributer distributer = new Distributer();
		CustomerYzw customer = new CustomerYzw();
		customer.setMemberCard("E53000116");
		distributer.setCustomer(customer);
		
		List<Distributer> distributers = distributerDao.findByExample(distributer);
		System.err.println(distributers.size());
	}
	
	@Test
	public void testFindByExample(){
		EmployeeYzw employee = new EmployeeYzw();
		employee.setId(1);
		List<EmployeeYzw> emps = employeeDao.findByExample(employee);
		System.err.println(emps.size());
	}
	
	@Test
	public void testFindContractByContractNo(){
		String contractNo = "YZW20170822153";
		try {
			System.out.println(orderYzwDao.findContractByContractNo(contractNo));
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testfindPageOfOrderApiViewByCustomerId(){
		int customerId = 33897;
		
		PageBean<OrderApiView> page = orderYzwDao.findPageOfOrderApiViewByCustomerId(customerId, 1, 10);
		List<OrderApiView> views = page.getData();
		Field[] fields = ReflectUtils.getAllFields(OrderApiView.class);
		for (OrderApiView view : views) {
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					System.err.println(views.indexOf(view)+ " " + field.getName() + " : " + field.get(view));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.err.println("\n");
		}
	}
}
