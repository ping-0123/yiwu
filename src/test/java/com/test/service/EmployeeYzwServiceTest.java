package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

public class EmployeeYzwServiceTest extends BaseSpringTest {
	
	@Autowired private EmployeeYzwService empService;
	
	@Test
	public void testFindAllCoaches(){
//		List<EmployeeApiView> coaches = empService.getAllOnJobCoaches();
//		System.err.println("coaches size is " + coaches.size());
//		System.err.println("coaches is " + new Gson().toJson(coaches, 
//				new TypeToken<List<EmployeeApiView>>() {}.getType()));
		
		System.err.println("aaaa");
	}
}
