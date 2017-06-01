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
import com.yinzhiwu.springmvc3.model.PlanRevenueApiModel;
import com.yinzhiwu.springmvc3.service.PlanRevenueService;


@Service
public class PlanRevenueServiceImpl implements PlanRevenueService {
	
	@Autowired
	private PlanRevenueDao prDao;
	
	@Autowired
	@Qualifier(value="departmentDaoImplTwo")
	private DepartmentDao departmentDao;

	@Override
	public PlanRevenueApiModel getStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month) {
		return new PlanRevenueApiModel(departmentDao,prDao.findStoreMonthlyPlanRevenue(storeId, productType, year, month));
	}
	
	
	@Override
	public List<PlanRevenueApiModel> getDistricMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId){
		
		List<Department>  stores = null;
		List<PlanRevenueApiModel> plans = new ArrayList<>();
		if (districtId>0)
			stores = departmentDao.findStoresByDistrictId(districtId);
		else
			stores=departmentDao.findAllStores();
		
		for (Department store : stores) {
			plans.add(new PlanRevenueApiModel(
					departmentDao,
					prDao.findStoreMonthlyPlanRevenue(store.getId(), productTypeId, year, month)));
		}
		
		return plans;
	}


	@Override
	public PlanRevenueApiModel get(int id) {
		try{
			return new PlanRevenueApiModel(
					departmentDao,prDao.get(id));
		}catch (Exception e) {
			return null;
		}
	}


	@Override
	public int save(PlanRevenue plan) {
		return prDao.save(plan);
	}


	@Override
	public void saveOrUpdate(PlanRevenue plan) {
		prDao.saveOrUpdate(plan);
	}


	@Override
	public void delete(int id) {
		 prDao.delete(id);
	}


	@Override
	public List<PlanRevenueApiModel> findAll() {
		List<PlanRevenueApiModel> planModels = new ArrayList<>();
		for (PlanRevenue plan : prDao.findAll()) {
			planModels.add(new PlanRevenueApiModel(departmentDao,plan));
		}
		return planModels;
	}


	@Override
	public List<PlanRevenueApiModel> findByExample(PlanRevenue plan) {
		List<PlanRevenueApiModel> planModels = new ArrayList<>();
		for (PlanRevenue p : prDao.findByExample(plan)) {
			planModels.add(new PlanRevenueApiModel(departmentDao,p));
		}
		return planModels;
	}
	
	
	@Override
	public List<PlanRevenueApiModel> findByProperties(
			int storeId ,int year, int month, int productTypeId){
		List<PlanRevenueApiModel> planModels = new ArrayList<>();
		for (PlanRevenue p : prDao.findByProperties(storeId, year, month, productTypeId)) {
			planModels.add(new PlanRevenueApiModel(departmentDao,p));
		}
		return planModels;
	}

}
