package com.test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.StoreInfoDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.entity.StoreInfo;
import com.yinzhiwu.springmvc3.model.Store;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_coursetable.xml")
public class TestCalendar {
	
	@Autowired
	private StoreInfoDao dao;
	
	@Autowired
	@Qualifier("departmentDaoImpl")
	private DepartmentDao deptDao;
	
	@Test
	public void testFirstDayOfWeek(){
		Calendar ca = Calendar.getInstance();
		ca.setFirstDayOfWeek(Calendar.SUNDAY);
		System.out.println(ca.get(Calendar.DAY_OF_WEEK));
		System.out.println(4/7);
	}
	
	@Test
	public void testGetClassName(){
		Store s = new Store();
		System.out.println(s.getClass().getSimpleName());
//		System.out.println(clazz.getSimpleName());
		

	}
	
	@Test
	public void testMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("", "aa");
		map.put("", "bb");
		map.put(null, "cc");
		System.out.println(map.get(null));
		System.out.println(map.size());
	}
	
	
	@Test
	public void testFindByProperties(){
		Map<String, Object> map = new HashMap<>();
		map.put("id",59);
		map.put("address", "南苑街道东湖南路284号");
		
		List<StoreInfo> l = dao.findByProperties(map);
		System.out.println(l.get(0).getAddress());
	}
	
	
	@Test
	public void testFindCities(){
		List<String> l = deptDao.findCities();
		System.out.println(l.size());
	}
	
	@Test
	public void testFindByProperty(){
		List<Department> deptL = deptDao.findByProperty("city", "杭州");
		System.out.println(deptL.size());
	}
}
