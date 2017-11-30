package com.yinzhiwu.yiwu.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月26日下午5:21:05
*
*/

public interface EmployeeYzwDao extends IBaseDao<EmployeeYzw, Integer>  {

	EmployeeYzw findByPhoneNo(String phoneNo) throws Exception;

	EmployeeYzw findByTel(String phoneNo);

	EmployeeYzw findByUsername(String username);

	List<EmployeeYzw> findByNullNumber();

	List<EmployeeYzw> findAll(Specification<EmployeeYzw> spec);


}
