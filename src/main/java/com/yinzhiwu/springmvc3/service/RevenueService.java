package com.yinzhiwu.springmvc3.service;

public interface RevenueService {

	Object[][] getMonthlyRevenue(int year, int month, int districtId, int productTypeId);
	
}
