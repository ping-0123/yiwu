package com.yinzhiwu.yiwu.service.impl;

import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.entity.PlanRevenue;
import com.yinzhiwu.yiwu.service.PlanRevenueService;

/**
*@Author ping
*@Time  创建时间:2017年9月13日下午7:52:27
*
*/

@Service
public class PlanRevenueServiceImpl extends BaseServiceImpl<PlanRevenue, Integer> implements PlanRevenueService{

	@Override
	public Object getDistricMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
