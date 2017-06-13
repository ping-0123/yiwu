package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.page.PageBean;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PageTest {

/*	@Autowired
	private OrderYzwDao orderYzwDao;
	*/
	@Autowired
	private DistributerDao distributerDao;
	
	@Test
	public void testPage(){
		String hql = "from Distributer where 1 = 1";
		for(int i =3; i<4; i++){
			PageBean<Distributer> page = distributerDao.findPageByHql(hql, i, 4);
			System.out.println(page.getTotalRecord());
			for (Distributer d : page.getList()) {
				System.out.println(d.getName());
			}
		}
	}
}
