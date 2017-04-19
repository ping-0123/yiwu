package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.dao.EmployeeDao;
import com.yinzhiwu.springmvc3.model.EmployeeApiView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_coursetable.xml")
public class Test3 {
	
	@Autowired
	private EmployeeDao empDao;
	
	@Test
	public void testEmpDao(){
		List<EmployeeApiView> emps = empDao.findAllOnJobCoach();
		System.out.println(emps.size());
	}
	 
}
