package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DepartmentDao;
import com.yinzhiwu.yiwu.dao.OrderDao;
import com.yinzhiwu.yiwu.dao.PlanRevenueDao;
import com.yinzhiwu.yiwu.entity.PlanRevenue;
import com.yinzhiwu.yiwu.entity.yzwOld.Department;
import com.yinzhiwu.yiwu.model.RevenueModel;
import com.yinzhiwu.yiwu.service.RevenueService;

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
		List<Department> stores = null;
		if(districtId ==0){
			stores= deptDao.findAllStores();
		}else{
			stores= deptDao.findStoresByDistrictId(districtId);
		}
		
		int cols = stores.size();
		// 设置开始和结束时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date start = calendar.getTime();
		
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date end = calendar.getTime();
		
		int rows = 1 + (int) ((end.getTime()-start.getTime())/(1000*60*60*24));
		
		RevenueModel[][] revenue = new RevenueModel[rows][cols];
	
		calendar.setTime(start);
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				RevenueModel r = new RevenueModel();
				r.setDate(new java.sql.Date(calendar.getTime().getTime()));
				r.setStoreId(stores.get(j).getId());
				r.setStoreName(stores.get(j).getDeptName());
				r.setAmount(0.0);
				revenue[i][j] = r;
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		//取出按天按店的营业额
		List<Object[]> revenues = orderDao.getMonthlyRevenue(districtId, productTypeId, start, end);
		
		for (Object[] objs : revenues) {
			try {
				revenue[getRowIndex((Date)objs[2])]
						[getCloumnIndex((int)objs[0], stores)]
						.setAmount((double)objs[3]);
			} catch (Exception e) {
//				logger.warn("storeId: " + objs[0] + " not found ");
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("revenues" , revenue);
		
		PlanRevenue[]  plans = new PlanRevenue[cols];
		for(int j=0; j<cols; j++){
			PlanRevenue p = prDao.findStoreMonthlyPlanRevenue(
					stores.get(j).getId(), productTypeId, year, month);
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
	
	private int getRowIndex(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return  calendar.get(Calendar.DAY_OF_MONTH) -1;
	}

	
	
}
