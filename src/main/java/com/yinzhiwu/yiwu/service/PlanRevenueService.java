package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.PlanRevenue;
import com.yinzhiwu.yiwu.model.PlanRevenueApiModel;

public interface PlanRevenueService {

	public PlanRevenueApiModel getStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month);

	public List<PlanRevenueApiModel> getDistricMonthlyPlanRevenue(int districtId, int year, int month,
			int productTypeId);

	public PlanRevenueApiModel get(int id);

	public int save(PlanRevenue plan);

	public void saveOrUpdate(PlanRevenue plan);

	public void delete(int id);

	public List<PlanRevenueApiModel> findAll();

	public List<PlanRevenueApiModel> findByExample(PlanRevenue plan);

	List<PlanRevenueApiModel> findByProperties(int storeId, int year, int month, int productTypeId);
}