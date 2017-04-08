package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.dao.PlanRevenueDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.model.RevenueList;
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
	
	
	@Override
	public Object[][] getMonthlyRevenue(int year, int month, int districtId, int productTypeId) {
		//取出该区域下所有的门店
		List<Department> storeList = deptDao.findStoresByDistrictId(districtId);
//		for (Department department : storeList) {
//			logger.info(department.getId() + department.getDeptName());
//		}
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
//		for (Object[] objects : revenueList) {
//			logger.info(objects[0] + " " + objects[1] + " " + objects[2]);
//		}
		
		for (Object[] objs : revenueList) {
			try {
				revenue[getRowIndex((Date)objs[2])]
						[getCloumnIndex((int)objs[0], storeList)]
						.setAmount((double)objs[3]);
			} catch (Exception e) {
				logger.warn("storeId: " + objs[0] + " not found ");
//				e.printStackTrace();
			}
		}
		
		return revenue;
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

	public List<RevenueList> getMonthlyRevenue1(int year, int month, int districtId, int productTypeId) {
		
		//取出该区域下所有的门店
		List<Department> storeList = deptDao.findStoresByDistrictId(districtId);
		
		// 设置开始和结束时间
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.MONTH, month-1);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date start = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date end = ca.getTime();
		
		//取出按天按店的营业额
		List<Object[]> revenueList = orderDao.getMonthlyRevenue(districtId, productTypeId, start, end);
		
		
//		Date date= start;
		ca.setTime(start);
		List<RevenueList> list = new ArrayList<>();
		Iterator<Object[]> it = revenueList.iterator();
		while(it.hasNext()){
			Date date = ca.getTime();
			List<RevenueModel> rmList = new ArrayList<>();
			Object[] obj = it.next();
			while (obj[2]==ca.getTime()){
				 for(int i=0; i<storeList.size(); i++){
					 RevenueModel rm = new RevenueModel();
					 if(obj[0]==storeList.get(i).getId()){
						 rm.setStoreId((int)obj[0]);
						 rm.setStoreName((String)obj[1]);
//						 rm.setDate((Date)obj[2]);
						 rm.setAmount((Double)obj[3]);
						 obj =it.next();
					 }else{
						 rm.setStoreId(storeList.get(i).getId());
						 rm.setStoreName(storeList.get(i).getDeptName());
//						 rm.setDate(ca.getTime());
						 rm.setAmount(0.0);
					 }
					 rmList.add(rm);
				 } 
			}
			
			list.add(new RevenueList(date, rmList));
			ca.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return null;
	}
	
}
