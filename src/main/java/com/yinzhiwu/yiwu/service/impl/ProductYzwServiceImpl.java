package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ProductYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.service.ProductYzwService;

/**
*@Author ping
*@Time  创建时间:2017年7月24日下午3:50:17
*
*/

@Service
public class ProductYzwServiceImpl extends BaseServiceImpl<ProductYzw,Integer> implements ProductYzwService {

		@Autowired public void setDao(ProductYzwDao productYzwDao){
			super.setBaseDao(productYzwDao);
		}
}
