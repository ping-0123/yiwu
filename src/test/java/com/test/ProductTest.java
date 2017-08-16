package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.web.purchase.dto.ProductDto;

/**
*@Author ping
*@Time  创建时间:2017年7月28日下午9:13:55
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductTest {

	@Autowired private ProductYzwDao productDao;
	
	@Transactional
	@Test
	public void test(){
		List<ProductDto> dtos = productDao.findByCardTypeByAgeTypeByCompany(1, ProductCardType.CHILDREN_MEMBER_CARD, CustomerAgeType.ADULT);
		System.err.println(dtos.size());
		
	}
}
