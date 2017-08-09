package com.yinzhiwu.yiwu.web.purchase.dto;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;

/**
*@Author ping
*@Time  创建时间:2017年7月27日上午11:40:00
*
*/

public class EmpDistributerDto {
	
	private int distributerId;
	private int employeeId;
	private String employeeName;
	public EmpDistributerDto(){}
	public EmpDistributerDto(Distributer d){
		if(d == null) throw new IllegalArgumentException();
		this.distributerId =d.getId();
		EmployeeYzw emp = d.getEmployee();
		if(emp != null){
			this.employeeId = emp.getId();
			this.employeeName = emp.getName();
		}
	}
	
	public int getDistributerId() {
		return distributerId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	
}
