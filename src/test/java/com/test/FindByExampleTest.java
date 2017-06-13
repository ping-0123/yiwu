package com.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.CustomerYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.yzw.Contract;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.service.DistributerService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FindByExampleTest {
	
	private static Log LOG  = LogFactory.getLog(FindByExampleTest.class);

	@Autowired
	private OrderYzwDao orderDao;
	
	@Autowired CustomerYzwDao customerDao;
	
	@Test
	public void test(){
		OrderYzw order = new OrderYzw();
//		order.setPayedAmount(3880f);
		Contract contract = new Contract();
		contract.setContractNo("YZW20170601006");
		order.setContract(contract);
		try {
			List<OrderYzw> orders = orderDao.findByExample(order);
			System.out.println(orders.size());
			System.out.println(orders.get(0).getMemberCardNo());
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	private CapitalAccountDao capitalAccountDao;
	
	@Test
	public void test2(){
		Distributer distributer = new Distributer();
		
		CapitalAccount account = new CapitalAccount();
		try {
			account= capitalAccountDao.get(2000001);
		} catch (DataNotFoundException e1) {
			LOG.error(e1);
			e1.printStackTrace();
		}
		distributer.setDefaultCapitalAccount(account);
		
		try {
			List<Distributer> distributers = distributerDao.findByExample(distributer);
			System.out.println(distributers.size());
		} catch (DataNotFoundException e) {
			LOG.error(e);
		}
	}
	
	@Autowired
	private DistributerService distributerService;
	
	@Test
	public void test3(){
		Distributer distributer = new Distributer();
		CapitalAccount capitalAccount = new CapitalAccount();
		capitalAccount.setId(2000000);
		distributer.setDefaultCapitalAccount(capitalAccount);
		try {
			distributerService.modify(3000002, distributer);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4(){
		try {
			Distributer distributer = distributerDao.get(3000002);
			distributer.setId(3000007);
			distributerDao.save(distributer);
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}
	}
}
