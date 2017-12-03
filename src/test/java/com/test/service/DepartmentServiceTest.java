package com.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;

/**
* @author 作者 ping
* @Date 创建时间：2017年12月3日 下午6:18:04
*
*/

public class DepartmentServiceTest  extends BaseSpringTest{
	
	@Autowired DepartmentYzwService deptService;
	
	@Test
	public void testFindCompany(){
		System.out.println("size is " +deptService.findCompanies().size());
	}

}
