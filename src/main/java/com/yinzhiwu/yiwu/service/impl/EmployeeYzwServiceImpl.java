package com.yinzhiwu.yiwu.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.dao.SequenceDao;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeePostYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.model.view.EmployeeApiView;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.UserService;

import io.swagger.annotations.Authorization;

/**
 * 
 * @author ping
 * @Date 2017年9月17日 下午9:21:17
 *
 */
@Service
public class EmployeeYzwServiceImpl extends BaseServiceImpl<EmployeeYzw, Integer> implements EmployeeYzwService {

	@Autowired private SequenceDao sequenceDao;
	@Autowired private UserService userService;
	@Autowired private EmployeeYzwDao empDao;
	
	@Autowired public void setBaseDao(EmployeeYzwDao dao){super.setBaseDao(dao);}
	

	/**
	 * 新员工自动分配工号， 自动创建系统账号
	 */
	@Override
	public Integer save(EmployeeYzw employee) {
		String empNumber = generateEmployeeNumber();
		employee.setNumber(empNumber);
		Integer id =  super.save(employee);
		
		User user = new User(empNumber);
		user.setEmployee(employee);
		userService.save(user);
		
		return id;
	}

	private String generateEmployeeNumber(){
		DateFormat df = new SimpleDateFormat("yyMMdd");
		Integer number = sequenceDao.getEmployeeNumber();
		return df.format(new Date()) + String.format("%02d", number%100);
	}

	@Override
	public Set<DepartmentYzw> getVisableDepartments(EmployeeYzw employee) {
		Set<EmployeePostYzw> eps = employee.getEmployeePosts();
		Set<DepartmentYzw> deptments = new HashSet<>();
		for(EmployeePostYzw ep: eps){
			deptments.addAll(ep.getDepartment().fimily());
		}
		
		return deptments;
	}
	
}
