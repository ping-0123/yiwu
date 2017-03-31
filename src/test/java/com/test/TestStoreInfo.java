package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.CheckInsDao;
import com.yinzhiwu.springmvc3.dao.StoreInfoDao;
import com.yinzhiwu.springmvc3.entity.StoreInfo;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_coursetable.xml")
public class TestStoreInfo {
	
	
	@Autowired
	private StoreInfoDao dao;
	
	@Autowired
	private CheckInsDao checkInsDao;
	
	@Test
	public void testDao(){
		StoreInfo s = dao.get(63);
		System.out.println(s.getAddress());
	}
	
	@Test
	public void testCheckIns(){
		System.out.println(checkInsDao.get(82).getContract());
	}
	
	@Test
	public void testFindCount(){
		System.out.println(checkInsDao.findCountByProperty("lessonId", "1463"));
	}
}
