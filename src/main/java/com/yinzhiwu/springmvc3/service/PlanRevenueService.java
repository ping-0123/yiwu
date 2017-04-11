package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.PlanRevenue;


public interface PlanRevenueService {
	
	public PlanRevenue getStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month);

	public List<PlanRevenue> getDistricMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId);
}