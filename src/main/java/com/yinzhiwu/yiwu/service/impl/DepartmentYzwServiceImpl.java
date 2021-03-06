package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.entity.sys.User;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw.OrgnizationType;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.EmployeeYzwService;
import com.yinzhiwu.yiwu.service.UserService;
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
	@Autowired private EmployeeYzwService empService;
	@Autowired private UserService userService;

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
	
	@Override
	public List<DepartmentYzw> findVisableStores() {
		User user = UserContext.getUser();
		if("Admin".equals(user.getUsername()))
			return findAllStores();
		EmployeeYzw emp = userService.findEmployeeByUserId(user.getId());
		if(null == emp)
			return new ArrayList<>();
		
		List<DepartmentYzw> stores = new ArrayList<>();
		List<DepartmentYzw> depts = empDeptDao.findDepartmentsByEmployee(emp.getId());
		for (DepartmentYzw dept : depts) {
			stores.addAll(getChildStores(dept));
		}
		return stores;
	}

	private List<DepartmentYzw> getChildStores(DepartmentYzw dept) {
		List<DepartmentYzw> stores = new ArrayList<>();
		if(OrgnizationType.STORE == dept.getType())
			stores.add(dept);
		else{
			for (DepartmentYzw child : dept.getChildren()) {
				stores.addAll(getChildStores(child));
			}
		}
		return stores;
	}

	@Override
	public List<DepartmentApiView> findAllOperationDistricts() {
		return departmentDao.findAllOperationDistricts();
	}

	@Override
	public List<StoreApiView> findStoreApiViewsUnderOrganization(Integer districtId) {
		return departmentDao.findStoreApiViewsUnderOrganization(districtId);
	}

	@Override
	public void move(DepartmentYzw source, DepartmentYzw target) {
		source.setParent(target);
		super.save(source);
	}

	@Override
	public List<DepartmentYzw> findAllWithExclude(DepartmentYzw source) {
		List<DepartmentYzw>  depts = findAll();
		for (DepartmentYzw dept : depts) {
			if(source.equals(dept)){
				depts.remove(dept);
				break;
			}
		}
		return depts;
	}

	@Override
	public List<DepartmentYzw> findByCity(String city) {
		return departmentDao.findByCity(city);
	}

	@Override
	public List<DepartmentYzw> findAllStores() {
		return departmentDao.findByType(OrgnizationType.STORE);
	}

	@Override
	public String translateCommaSeparateIdsToNames(String commaSeparateIds) {
		if(!StringUtils.hasText(commaSeparateIds))
			return null;
		
		String[] storeIds = commaSeparateIds.split(",");
		String storeNames = "";
		for (String id : storeIds) {
			try {
				String name = get(Integer.valueOf(id)).getName();
				if(storeNames == "")
					storeNames = name;
				else {
					storeNames = storeNames + "," + name;
				}
			} catch (NumberFormatException | DataNotFoundException e) {
				continue;
			}
		}
		
		return storeNames;
	}

	@Override
	public List<DepartmentYzw> findCompanies() {
		return departmentDao.findByType(OrgnizationType.COMPANY);
	}

	
}
