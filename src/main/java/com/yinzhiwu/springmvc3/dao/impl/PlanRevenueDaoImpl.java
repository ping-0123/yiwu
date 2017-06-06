package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.PlanRevenueDao;
import com.yinzhiwu.springmvc3.entity.PlanRevenue;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;


@Repository
public class PlanRevenueDaoImpl extends BaseDaoImpl<PlanRevenue, Integer> implements PlanRevenueDao {

	@Override
	public PlanRevenue findStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month) {
		try{
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
		}catch (DataNotFoundException e) {
			return new PlanRevenue(storeId,year,month,0.0);
		}
	}

	@Override
	public List<PlanRevenue> findDistrictMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId){
//		StringBuilder hql=new StringBuilder();
		return null;
	}

	@Override
	public List<PlanRevenue> findByProperties(
			int storeId, int year, int month , int productTypeId)
	{
		try{
			if(storeId==0){
				if(productTypeId==0){
					return findByProperties(
							new String[]{"year",  "month"},
							new Object[]{year, month});
				}
				else{
					return findByProperties(
							new String[]{"year",  "month","productType.id"},
							new Object[]{year, month,productTypeId});
				}
			}else
				if(productTypeId==0){
					return findByProperties(
							new String[]{"storeId","year",  "month"},
							new Object[]{storeId,year, month});
				}
				return findByProperties(
						new String[]{"storeId", "year", "month", "productType.id"},
						new Object[]{storeId, year, month, productTypeId});
		}catch (DataNotFoundException e) {
			return new ArrayList<>();
		}
	}
}
