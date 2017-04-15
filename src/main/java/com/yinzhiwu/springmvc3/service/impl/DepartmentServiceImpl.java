package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.StoreInfoDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.entity.StoreInfo;
import com.yinzhiwu.springmvc3.model.BriefDepartment;
import com.yinzhiwu.springmvc3.model.Store;
import com.yinzhiwu.springmvc3.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	@Qualifier("departmentDaoImplTwo")
	private DepartmentDao departmentDao;
	
	@Autowired
	private StoreInfoDao storeInfoDao;

	@Override
	public List<BriefDepartment> findAllOperationDistricts() {
//		List<Department> list = departmentDao.findAllOperationDistricts();
		Map<String, Object> map = new HashMap<>();
		map.put("superiorId", 55);
		map.put("removed", 0);
		List<Department> list = departmentDao.findByProperties(map);
		List<BriefDepartment> l = new ArrayList<>();
		for (Department d : list) {
			l.add(new BriefDepartment(d));
		}
		return l;
	}

	@Override
	public List<BriefDepartment> findStoresByDistrictId(int districtId) {
		List<Department> list = departmentDao.findStoresByDistrictId(districtId);
		List<BriefDepartment> l = new ArrayList<>();
		for (Department d : list) {
			l.add(new BriefDepartment(d));
		}
		return l;
	}

	@Override
	public Department findById(int id) {
		return departmentDao.findById(id);
	}

	@Override
	public void show() {
		departmentDao.show();
	}

	@Override
	public List<Store> findStoreInfosByDistrictId(int districtId) {
		List<Department> deptList = departmentDao.findStoresByDistrictId(districtId);
		List<Store> storeList = new ArrayList<>();
		for (Department d : deptList) {
			Store s = new Store();
			s.setId(d.getId());
			s.setName(d.getDeptName());
			StoreInfo sf = storeInfoDao.get(d.getId());
			s.setAddress(sf.getAddress());
			s.setTelePhone(sf.getTelePhone());
			
			storeList.add(s);
		}
		return storeList;
	}

	@Override
	public Store findStoreInfoById(int id) {
		Department dept = departmentDao.findById(id);
		StoreInfo sf = storeInfoDao.get(id);
		Store s = new Store(sf);
		s.setName(dept.getDeptName());
		return s;
	}
	
	@Override
	public List<Store> findStoreByCities(String city){
		List<Department> deptList = departmentDao.findByProperty("city", city);
		List<Store> storeList = new ArrayList<>();
		for (Department d : deptList) {
			Store s = new Store(d);
//			s.setId(d.getId());
//			s.setName(d.getDeptName());
//			StoreInfo sf = storeInfoDao.get(d.getId());
//			s.setAddress(sf.getAddress());
//			s.setTelePhone(sf.getTelePhone());
//			
			storeList.add(s);
		}
		return storeList;
		
	}

}
