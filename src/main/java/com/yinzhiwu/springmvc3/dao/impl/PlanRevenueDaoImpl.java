package com.yinzhiwu.springmvc3.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.PlanRevenueDao;
import com.yinzhiwu.springmvc3.entity.PlanRevenue;


@Repository
public class PlanRevenueDaoImpl extends BaseDaoImpl<PlanRevenue, Integer> implements PlanRevenueDao {

	@Override
	public PlanRevenue findStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month) {
		List<PlanRevenue> list = null;
		Map<String, Object> map = new HashMap<>();
		double sum=0.0;
		map.put("month", month);
		map.put("year", year);
		map.put("storeId", storeId);
		if (productType>0){
			map.put("productType.id", productType);
			list = findByProperties(map);
			if (list.size()>0)
				return list.get(0);
			else
				return new PlanRevenue(storeId,year, month,0.0);
		}
		
		list = findByProperties(map);
		for (PlanRevenue planRevenue : list) {
			sum = sum + planRevenue.getAmount();
		}
		return new PlanRevenue(storeId,year, month,sum);
	}

	@Override
	public List<PlanRevenue> findDistrictMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId){
		StringBuilder hql=new StringBuilder();
		
		return null;
	}

}
