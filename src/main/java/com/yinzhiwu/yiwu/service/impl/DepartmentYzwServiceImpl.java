package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.web.purchase.dto.StoreDto;

/**
*@Author ping
*@Time  创建时间:2017年7月28日上午9:08:33
*
*/

@Service
public class DepartmentYzwServiceImpl extends BaseServiceImpl<DepartmentYzw, Integer> implements DepartmentYzwService{

	@Autowired
	public void setBaseDao(DepartmentYzwDao departmentYzwDao){
		super.setBaseDao(departmentYzwDao);
	}
	
	@Autowired private DepartmentYzwDao departmentDao;
//	@Autowired private EmployeePostYzwDao empPostDao;
	@Autowired private EmployeeDepartmentYzwDao empDeptDao;

	@Override
	public List<StoreDto> findAllStores(int employeeId) {
		//TODO 找出员工所在的公司
		int companyId = 55;
		List<StoreDto> stores = new ArrayList<>();
		List<DepartmentYzw> depts = departmentDao.findAllStoresUnderOrganization(companyId);
		for (DepartmentYzw dept : depts) {
			stores.add(new StoreDto(dept));
		}
		return stores;
	}

	@Override
	public List<StoreDto> findVisableStoresByEmployee(int employeeId) {
		//设置教务部教师可见整个公司的门店
		List<DepartmentYzw> depts = empDeptDao.findDepartmentsByEmployee(employeeId);
		for (DepartmentYzw dept : depts) {
			if(dept.getPath().contains("51")){
				DepartmentYzw d = new DepartmentYzw();
				d.setId(48);
				depts.removeAll(depts);
				depts.add(d);
				break;
			}
		}
		List<StoreDto> dtos = new ArrayList<>();
		List<DepartmentYzw> stores = departmentDao.findAllStoresUnderOrganizations(depts);
		for (DepartmentYzw store : stores) {
			dtos.add(new StoreDto(store));
		}
		return dtos;
	}
	
}
