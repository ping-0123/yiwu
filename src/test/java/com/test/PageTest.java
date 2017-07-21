package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.model.page.PageBean;


@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(BlockJUnit4ClassRunner.class)
public class PageTest {

/*	@Autowired
	private OrderYzwDao orderYzwDao;
	*/
	@Autowired
	private DistributerDao distributerDao;
	
	@Test
	public void testSplit(){
		String a ="bbbb.cc.dd";
		String[] as = a.split("\\.");
		for (String string : as) {
			System.out.println(string);
		}
	}
	
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
