package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.model.RevenueList;

public interface RevenueService {

	List<RevenueList> getMonthlyRevenue(int year, int month, int districtId, int productTypeId);
	
}
