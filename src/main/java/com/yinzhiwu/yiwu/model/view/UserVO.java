package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.enums.DataStatus;
import com.yinzhiwu.yiwu.util.beanutils.AbstractVO;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
*@Author ping
*@Time  创建时间:2017年10月16日下午8:31:30
*
*/

@MapedClass(User.class)
public class UserVO extends AbstractVO<User, UserVO> {
	
	private Integer id;
	private String username;
	@MapedProperty(value="employee.id")
	private Integer employeeId;
	@MapedProperty(value="employee.name")
	private String employeeName;
	private DataStatus dataStatus;
	
	
	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public DataStatus getDataStatus() {
		return dataStatus;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public void setDataStatus(DataStatus dataStatus) {
		this.dataStatus = dataStatus;
	}
	
	
}
