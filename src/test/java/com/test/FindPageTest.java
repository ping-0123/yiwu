package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;

//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FindPageTest {

	@Autowired
	DistributerDao distributerDao;

	@Test
	public void test() {
		PageBean<Distributer> page = distributerDao.findPageOfAll(1, 5);
		System.out.println(page.getData().size());
		for (Distributer d : page.getData()) {
			System.out.println(d.getPhoneNo());
		}
	}

	@Test
	public void testModify() {
		Distributer taget = new Distributer();
		taget.setUsername("aaaaaaaaaaa");
		try {
			distributerDao.modify(3000020, taget);
			System.err.println(distributerDao.get(3000020).getUsername());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
