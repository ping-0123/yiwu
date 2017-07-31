package com.yinzhiwu.yiwu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.PlanRevenueDao;
import com.yinzhiwu.yiwu.entity.PlanRevenue;

@Repository
public class PlanRevenueDaoImpl extends BaseDaoImpl<PlanRevenue, Integer> implements PlanRevenueDao {

	@Override
	public PlanRevenue findStoreMonthlyPlanRevenue(int storeId, int productType, int year, int month) {
		String[] properties;
		Object[] values;
		if(productType > 0){
			properties = new String[]{"storeId", "productType.id", "year", "month"};
			values = new Object[]{storeId, productType, year, month};
		}else{
			properties = new String[]{"storeId", "year", "month"};
			values = new Object[]{storeId,year, month};
		}
		
		List<PlanRevenue> revenues = findByProperties(properties, values);
		if(revenues.size() == 0)
			return new PlanRevenue(storeId, year, month, 0.0);
		else if (revenues.size() ==1) {
			return revenues.get(0);
		}else {
			double sum = 0; //总营业计划
			for (PlanRevenue r : revenues) {
				sum = sum + r.getAmount();
			}
			return new PlanRevenue(storeId, year, month, sum);
		}
		
	}

	@Override
	public List<PlanRevenue> findDistrictMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId) {
		// StringBuilder hql=new StringBuilder();
		//TODO
		return null;
	}

	@Override
	public List<PlanRevenue> findByProperties(int storeId, int year, int month, int productTypeId) {
			if (storeId == 0) {
				if (productTypeId == 0) {
					return findByProperties(new String[] { "year", "month" }, new Object[] { year, month });
				} else {
					return findByProperties(new String[] { "year", "month", "productType.id" },
							new Object[] { year, month, productTypeId });
				}
			} else if (productTypeId == 0) {
				return findByProperties(new String[] { "storeId", "year", "month" },
						new Object[] { storeId, year, month });
			}
			return findByProperties(new String[] { "storeId", "year", "month", "productType.id" },
					new Object[] { storeId, year, month, productTypeId });
	}
}
