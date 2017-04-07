package com.test;

import java.util.Calendar;
import java.util.Date;
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
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.dao.StoreInfoDao;
import com.yinzhiwu.springmvc3.dao.StoreManCallRollDao;
import com.yinzhiwu.springmvc3.dao.TeacherCallRollDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.entity.StoreInfo;
import com.yinzhiwu.springmvc3.entity.StoreManCallRoll;
import com.yinzhiwu.springmvc3.entity.TeacherCallRoll;
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
	
	
	@Autowired
	private TeacherCallRollDao tcrDao;
	
	@Autowired
	private StoreManCallRollDao scrDao;
	
	@Test
	public void testEntity(){
		TeacherCallRoll t = tcrDao.get(9);
		System.out.println(t.getCourseType());
		StoreManCallRoll s = scrDao.get(186);
		System.out.println(s.getMemberCard());
	}
	
	@Test
	public void testSCRcount(){
	}
	
	@Autowired
	private OrderDao ordDao;
	
	@Test
	public void testFindPerformace(){
		Calendar ca = Calendar.getInstance();
		Date end = ca.getTime();
		ca.add(Calendar.DAY_OF_MONTH, -30);
		Date start = ca.getTime();
		List<Object[]> l = ordDao.getMonthlyRevenue(113,3 , start, end);
		for (Object[] objs : l) {
			for (Object object : objs) {
				System.out.println(object + "  ");
			}
		}
		System.out.println(l.size());
	}
	
	@Test
	public void testCalendarCompareTo(){
		Calendar start =Calendar.getInstance();
		start.set(Calendar.MONTH, 2);
//		start.set(Calendar.YEAR,2015);
		Calendar end = Calendar.getInstance();
		int s = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
		System.out.println(s);
		System.out.println((end.getTimeInMillis()-start.getTimeInMillis())/(1000*60*60*24));
	}
}
