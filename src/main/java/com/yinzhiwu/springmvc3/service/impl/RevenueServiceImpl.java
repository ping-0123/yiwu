package com.yinzhiwu.springmvc3.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.dao.PlanRevenueDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.entity.PlanRevenue;
import com.yinzhiwu.springmvc3.model.RevenueModel;
import com.yinzhiwu.springmvc3.service.RevenueService;

@Service
public class RevenueServiceImpl implements RevenueService{
	
	static Logger logger = Logger.getLogger(RevenueServiceImpl.class);
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	@Qualifier("departmentDaoImplTwo")
	private DepartmentDao deptDao;
	
	@Autowired
	private PlanRevenueDao prDao;
	
	
	@Override
	public Object getMonthlyRevenue(int year, int month, int districtId, int productTypeId) {
		//取出该区域下所有的门店
		List<Department> storeList = null;
		if(districtId ==0){
			storeList= deptDao.findAllStores();
		}else{
			storeList= deptDao.findStoresByDistrictId(districtId);
		}
		
		int cols = storeList.size();
		// 设置开始和结束时间
		Calendar sc = Calendar.getInstance();
		sc.set(Calendar.YEAR, year);
		sc.set(Calendar.MONTH, month-1);
		sc.set(Calendar.DAY_OF_MONTH, 1);
		Date start = sc.getTime();
		
		sc.add(Calendar.MONTH, 1);
		sc.add(Calendar.DAY_OF_MONTH, -1);
		Date end = sc.getTime();
		
		int rows = 1 + (int) ((end.getTime()-start.getTime())/(1000*60*60*24));
		
		RevenueModel[][] revenue = new RevenueModel[rows][cols];
	
		sc.setTime(start);
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				RevenueModel r = new RevenueModel();
				r.setDate(new java.sql.Date(sc.getTime().getTime()));
				r.setStoreId(storeList.get(j).getId());
				r.setStoreName(storeList.get(j).getDeptName());
				r.setAmount(0.0);
				revenue[i][j] = r;
			}
			sc.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		//取出按天按店的营业额
		List<Object[]> revenueList = orderDao.getMonthlyRevenue(districtId, productTypeId, start, end);
		
		for (Object[] objs : revenueList) {
			try {
				revenue[getRowIndex((Date)objs[2])]
						[getCloumnIndex((int)objs[0], storeList)]
						.setAmount((double)objs[3]);
			} catch (Exception e) {
				logger.warn("storeId: " + objs[0] + " not found ");
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("revenues" , revenue);
		
		PlanRevenue[]  plans = new PlanRevenue[cols];
		for(int j=0; j<cols; j++){
			PlanRevenue p = prDao.findStoreMonthlyPlanRevenue(
					storeList.get(j).getId(), productTypeId, year, month);
			plans[j] = p;
		
		}

		map.put("plans", plans);
		return map;
	}
	
	
	private int getCloumnIndex(int storeId, List<Department> list) throws Exception{
		for(int i =0; i< list.size(); i++){
			if(storeId == list.get(i).getId()){
				return i;
			}
		}
		throw new Exception("storeId: " + storeId + " not found " );
	}
	
	@SuppressWarnings("deprecation")
	private int getRowIndex(Date date){
		return date.getDate() -1;
	}

	
	
}
