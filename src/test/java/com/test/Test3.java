package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.dao.EmployeeDao;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.MoneyRecordType;
import com.yinzhiwu.springmvc3.model.EmployeeApiView;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.service.RecordTypeService;
import com.yinzhiwu.springmvc3.util.GeneratorUtil;
import com.yinzhiwu.springmvc3.util.ShareCodeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test3 {
	
	@Autowired
	private EmployeeDao empDao;
	
	
	@Test
	public void testEmpDao(){
		List<EmployeeApiView> emps = empDao.findAllOnJobCoach();
		System.out.println(emps.size());
	}
	
	
	@Autowired
	private DistributerService distributerService;
	
	
	//@Test
	public void testOneToOne(){
		Customer customer = new Customer();
		customer.setId(21);
		Distributer d = new Distributer();
//		d.setCustomer(customer);
		d.setId(21);
		d.setShareCode(ShareCodeUtil.toSerialCode(d.getId()));
		d.setMemberId("aaa");
		System.out.println(d.getCreateDate());
		distributerService.save(d);
	}
	
	@Test
	public void testShareCodeUtil(){
		String shareCode = ShareCodeUtil.toSerialCode(2);
		System.out.println(shareCode);
		System.out.println(ShareCodeUtil.codeToId("sfsfss"));
	}
	

	@Test
	public void testGenerateMemberId(){
		System.out.println(GeneratorUtil.generateMemberId(10));
	}
	
	@Autowired
	private RecordTypeService recordTypeService;
	
//	@Test
//	public void testRecordTypeService(){
//		ExpRecordType recordType = new ExpRecordType("注册2", 10);
//		recordType.setComments("二级客户注册获取经验值");
//		recordTypeService.save(recordType);
//	}
	
	//@Test
	public void saveMoneyRecordType(){
		MoneyRecordType moneyRecordType =new MoneyRecordType("注册","一级客户注册获取基金", 50);
		recordTypeService.save(moneyRecordType);
	}
	
	
}
