package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.StoreInfoDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.entity.StoreInfo;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.Store;
import com.yinzhiwu.springmvc3.model.view.DepartmentApiView;
import com.yinzhiwu.springmvc3.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private static Log LOG = LogFactory.getLog(DepartmentServiceImpl.class);
	
	@Autowired
	@Qualifier("departmentDaoImplTwo")
	private DepartmentDao departmentDao;
	
	@Autowired
	private StoreInfoDao storeInfoDao;

	@Override
	public List<DepartmentApiView> findAllOperationDistricts() {
		List<Department> list = departmentDao.findAllOperationDistricts();
		List<DepartmentApiView> l = new ArrayList<>();
		for (Department d : list) {
			l.add(new DepartmentApiView(d));
		}
		
		return l;
	}

	@Override
	public List<DepartmentApiView> findStoresByDistrictId(int districtId) {
		List<Department> list = departmentDao.findStoresByDistrictId(districtId);
		List<DepartmentApiView> l = new ArrayList<>();
		for (Department d : list) {
			l.add(new DepartmentApiView(d));
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
			try{
				StoreInfo sf = storeInfoDao.get(d.getId());
				s.setAddress(sf.getAddress());
				s.setTelePhone(sf.getTelePhone());
			}catch (Exception e) {
				LOG.warn(e.getMessage());
			}
			
			storeList.add(s);
		}
		return storeList;
	}

	@Override
	public Store findStoreInfoById(int id) {
		Department dept = departmentDao.findById(id);
		try{
			StoreInfo sf = storeInfoDao.get(id);
			Store s = new Store(sf);
			s.setName(dept.getDeptName());
			return s;
		}catch(DataNotFoundException e){
			LOG.warn(e.getMessage());
			return new Store(dept);
		}
		
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

	@Override
	public List<Store> getAllStores() {
		List<Department> depts=  departmentDao.findAllStores();
		List<Store> stores = new ArrayList<>();
		for (Department dept : depts) {
			stores.add(new Store(dept));
		}
		
		return stores;
	}

}
