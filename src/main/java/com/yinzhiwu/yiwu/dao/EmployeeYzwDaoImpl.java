package com.yinzhiwu.yiwu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午5:21:39
*
*/

@Repository
public class EmployeeYzwDaoImpl extends BaseDaoImpl<EmployeeYzw,Integer> implements EmployeeYzwDao {

	@Override
	public EmployeeYzw findByPhoneNo(String phoneNo) {
		try {
			List<EmployeeYzw> emps =findByProperties(
					new String[]{"cellPhone", "removed"}, 
					new Object[]{phoneNo, false});
			if(emps.size()> 1){
				logger.error(phoneNo + "在 employee表中重复");
			}
			return emps.get(0);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

}
