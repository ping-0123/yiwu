package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;

@Repository
public class OrderYzwDaoImpl extends BaseDaoImpl<OrderYzw, String>  implements OrderYzwDao{

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public String find_last_id() {
		String sql = "select id from vorder order by sf_create_time desc limit 1";
		List<String> list = getSession().createNativeQuery(sql).list();
		if(list.size()>0)
			return list.get(0);
		else
			return "20000101001";
	}

}
