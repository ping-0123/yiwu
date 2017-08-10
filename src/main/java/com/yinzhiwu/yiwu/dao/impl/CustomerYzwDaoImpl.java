package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Member;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.MemberStatus;

@Repository
public class CustomerYzwDaoImpl extends BaseDaoImpl<CustomerYzw, Integer> implements CustomerYzwDao {

	@Override
	public CustomerYzw findByWeChat(String weChatNo) {
		List<CustomerYzw> list = findByProperty("weChat", weChatNo);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public CustomerYzw findByPhoneNo(String phoneNo) {
		List<CustomerYzw> customers =  findByProperty("mobilePhone", phoneNo);
		CustomerYzw customer = null;
		switch (customers.size()) {
		case 0:
			return null;
		case 1:
			return customers.get(0);
		default:
			for (CustomerYzw cust : customers) {
				if(MemberStatus.MEMBER ==cust.getIsMember())
					customer = cust;
			}
			if(customer == null){
				customer = customers.get(customers.size()-1);
			}
		}
		return customer;
	}

	@Override
	public CustomerYzw findByPhoneByWechat(String phoneNo, String wechatNo) {
		List<CustomerYzw> customers = findByProperties(
				new String[]{"mobilePhone", "weChat"},
				new Object[]{phoneNo,wechatNo});
		if(customers.size() ==0) return null;
		return customers.get(0);
	}

}
