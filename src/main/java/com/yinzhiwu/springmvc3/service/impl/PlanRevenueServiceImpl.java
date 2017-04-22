package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.PlanRevenueDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.entity.PlanRevenue;
import com.yinzhiwu.springmvc3.service.PlanRevenueService;


@Service
public class PlanRevenueServiceImpl implements PlanRevenueService {
	
	@Autowired
	private PlanRevenueDao prDao;
	
	@Autowired
	@Qualifier(value="departmentDaoImplTwo")
	private DepartmentDao departmentDao;

	@Override
	public PlanRevenue getStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month) {
		return prDao.findStoreMonthlyPlanRevenue(storeId, productType, year, month);
	}
	
	
	@Override
	public List<PlanRevenue> getDistricMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId){
		
		List<Department>  stores = null;
		List<PlanRevenue> plans = new ArrayList<>();
		if (districtId>0)
			stores = departmentDao.findStoresByDistrictId(districtId);
		else
			stores=departmentDao.findAllStores();
		
		for (Department store : stores) {
			plans.add(prDao.findStoreMonthlyPlanRevenue(store.getId(), productTypeId, year, month));
		}
		
		return plans;
	}


	@Override
	public PlanRevenue get(int id) {
		return prDao.get(id);
	}


	@Override
	public int save(PlanRevenue plan) {
		return prDao.save(plan);
	}


	@Override
	public int saveOrUpdate(PlanRevenue plan) {
		return prDao.saveOrUpdate(plan);
	}


	@Override
	public void delete(int id) {
		 prDao.delete(id);
	}

}
