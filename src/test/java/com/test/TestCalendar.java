package com.test;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.StoreInfoDao;
import com.yinzhiwu.springmvc3.entity.StoreInfo;
import com.yinzhiwu.springmvc3.model.Store;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_coursetable.xml")
public class TestCalendar {
	
	@Autowired
	private StoreInfoDao dao;
	
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
	public void testGenericFindByProperty(){
		List<StoreInfo> l= dao.findByProperty("", "");
		System.out.println(l.get(0).getTelePhone());
	}
}
