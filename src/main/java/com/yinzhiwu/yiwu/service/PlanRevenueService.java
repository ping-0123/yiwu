package com.yinzhiwu.yiwu.service;

import com.yinzhiwu.yiwu.entity.PlanRevenue;

/**
*@Author ping
*@Time  创建时间:2017年9月13日下午7:37:03
*
*/

public interface PlanRevenueService extends IBaseService<PlanRevenue, Integer>{

	Object getDistricMonthlyPlanRevenue(int districtId, int year, int month, int productTypeId);

}
