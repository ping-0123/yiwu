package com.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.common.entity.search.MinMaxPair;
import com.yinzhiwu.yiwu.common.entity.search.PropertySpecification;
import com.yinzhiwu.yiwu.common.entity.search.SearchOperator;
import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;

public class EmployeeYzwServiceTest extends BaseSpringTest {
	
	@Autowired private EmployeeYzwService empService;
	@Autowired private EmployeeYzwDao empDao;
	
	@Test
	public void testFindAllCoaches(){
//		List<EmployeeApiView> coaches = empService.getAllOnJobCoaches();
//		System.err.println("coaches size is " + coaches.size());
//		System.err.println("coaches is " + new Gson().toJson(coaches, 
//				new TypeToken<List<EmployeeApiView>>() {}.getType()));
		
		System.err.println("aaaa");
	}
	
	@Rollback(false)
	@Test
	public void testUpdateNumber(){
		int empId = 1;
		try {
			EmployeeYzw emp = empService.get(1);
//			empService.updateNumberOfOldEmployee(emp);
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindAllByCriteria(){
		Specification<EmployeeYzw> spec = new PropertySpecification<>("id", SearchOperator.eq,1);
		List<EmployeeYzw> emps = empDao.findAll(spec);
		System.err.println(emps.size());
	}
	
	@Test
	public void testFindAllByCriteria2(){
		Specification<EmployeeYzw> spec = new PropertySpecification<>("username", SearchOperator.like,"180");
		List<EmployeeYzw> emps = empDao.findAll(spec);
		System.err.println(emps.size());
	}
	
	@Test
	public void testSpecifications(){
		Specification<EmployeeYzw> spec = new PropertySpecification<>("id", SearchOperator.between, new MinMaxPair<Integer>(100,200));
		Specifications<EmployeeYzw> spencs = Specifications.where(new PropertySpecification<EmployeeYzw>("username", SearchOperator.like, "1")).and(spec);
		List<EmployeeYzw> emps = empDao.findAll(spencs);
		System.err.println(emps.size());
	}
	
	@Test
	public void testIn(){
		Specification<EmployeeYzw> spec = new PropertySpecification<>("id", SearchOperator.in,new Integer[]{1,100});
		List<EmployeeYzw> emps = empDao.findAll(spec);
		System.err.println(emps.size());
	}
	
	@Test
	public void testNotIn(){
		Specification<EmployeeYzw> spec = new PropertySpecification<>("id", SearchOperator.notIn,new Integer[]{1,100});
		List<EmployeeYzw> emps = empDao.findAll(spec);
		System.err.println(emps.size());
	}
	
	@Test
	public void testIsNull(){
		Specification<EmployeeYzw> spec = new PropertySpecification<>("id", SearchOperator.isNull);
		List<EmployeeYzw> emps = empDao.findAll(spec);
		System.err.println(emps.size());
	}
}
