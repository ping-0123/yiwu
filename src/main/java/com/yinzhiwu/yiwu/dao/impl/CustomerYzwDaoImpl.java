package com.yinzhiwu.yiwu.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;

@Repository
public class CustomerYzwDaoImpl extends BaseDaoImpl<CustomerYzw, Integer> implements CustomerYzwDao {

	@Override
	public CustomerYzw findByWeChat(String weChatNo) {
		List<CustomerYzw> customers = findByProperty("weChat", weChatNo);
		return choseValidOne(customers);
	}

	@Override
	public CustomerYzw findByPhoneNo(String phoneNo) {
		List<CustomerYzw> customers =  findByProperty("mobilePhone", phoneNo);
		return choseValidOne(customers);
	}

	@Override
	public CustomerYzw findByPhoneByWechat(String phoneNo, String wechatNo) {
		List<CustomerYzw> customers = findByProperties(
				new String[]{"mobilePhone", "weChat"},
				new Object[]{phoneNo,wechatNo});
		return choseValidOne(customers);
	}

	private CustomerYzw choseValidOne(List<CustomerYzw> customers) {
		switch (customers.size()) {
		case 0:
			return null;
		case 1:
			return customers.get(0);
		default:
			for (CustomerYzw customer : customers) {
				if(existValidOpenTypeContracts(customer.getId()))
					return customer;
				if(existValidPrivateTypeContracts(customer.getId()))
					return customer;
				if(existValidClosedTypeContracts(customer.getId()))
					return customer;
			}
			return customers.get(0);
		}
	}

	private boolean existValidOpenTypeContracts(Integer customerId) {
		return existValidContracts(customerId, CourseType.OPENED);
	}
	private boolean existValidPrivateTypeContracts(Integer customerId) {
		return existValidContracts(customerId, CourseType.OPENED);
	}
	private boolean existValidClosedTypeContracts(Integer customerId) {
		return existValidContracts(customerId, CourseType.OPENED);
	}

	private boolean existValidContracts(Integer customerId, CourseType courseType) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT COUNT(1)");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.customer.id=:customerId");
		hql.append(" AND t1.contract.status IN :unExpirecdContractStatus");
		hql.append(" AND t1.contract.type= :courseType");
		hql.append(" AND t1.contract.remainTimes>=:remainTimes");
		hql.append(" AND t1.contract.end >=:curdate");
		Long count =   getSession().createQuery(hql.toString(), Long.class)
				.setParameter("customerId", 	customerId)
				.setParameter("validContractStatus", ContractStatus.getUnExpiredStatus())
				.setParameter("subCourseType", 	courseType)
				.setParameter("remainTimes", 	BigDecimal.valueOf(1))
				.setParameter("curdate", 		new Date())
				.getSingleResult();
		
		return count> 0;

	}
}
