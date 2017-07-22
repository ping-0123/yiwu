package com.yinzhiwu.yiwu.dao;

import java.util.List;

import com.yinzhiwu.yiwu.entity.PlanRevenue;

public interface PlanRevenueDao extends IBaseDao<PlanRevenue, Integer> {


	PlanRevenue findStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month);

	List<PlanRevenue> findDistrictMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId);

	List<PlanRevenue> findByProperties(int storeId, int year, int month, int productTypeId);
	
}
