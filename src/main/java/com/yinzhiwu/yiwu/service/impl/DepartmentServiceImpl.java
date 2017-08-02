package com.yinzhiwu.yiwu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DepartmentDao;
import com.yinzhiwu.yiwu.dao.StoreInfoDao;
import com.yinzhiwu.yiwu.entity.StoreInfo;
import com.yinzhiwu.yiwu.entity.yzwOld.Department;
import com.yinzhiwu.yiwu.model.view.DepartmentApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.service.DepartmentService;

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
	public List<StoreApiView> findStoreInfosByDistrictId(int districtId) {
		List<Department> deptList = departmentDao.findStoresByDistrictId(districtId);
		List<StoreApiView> storeList = new ArrayList<>();
		for (Department d : deptList) {
			StoreApiView s = new StoreApiView();
			s.setId(d.getId());
			s.setName(d.getDeptName());
			try {
				StoreInfo sf = storeInfoDao.get(d.getId());
				if (sf.getAddress() != null)
					s.setAddress(sf.getAddress().getAddress());
				s.setTelePhone(sf.getTelePhone());
			} catch (Exception e) {
				LOG.warn(e.getMessage());
			}

			storeList.add(s);
		}
		return storeList;
	}

	@Override
	public StoreApiView findStoreInfoById(int id) {
		Department dept = departmentDao.findById(id);
		StoreInfo sf = storeInfoDao.get(id);
		StoreApiView s = new StoreApiView(sf);
		s.setName(dept.getDeptName());
		return s;

	}

	@Override
	public List<StoreApiView> findStoreByCities(String city) {
		List<Department> deptList = departmentDao.findByProperty("city", city);
		List<StoreApiView> storeList = new ArrayList<>();
		for (Department d : deptList) {
			StoreApiView s = new StoreApiView(d);
			storeList.add(s);
		}
		return storeList;

	}

	@Override
	public List<StoreApiView> getAllStores() {
		List<Department> depts = departmentDao.findAllStores();
		List<StoreApiView> stores = new ArrayList<>();
		for (Department dept : depts) {
			stores.add(new StoreApiView(dept));
		}

		return stores;
	}

}
