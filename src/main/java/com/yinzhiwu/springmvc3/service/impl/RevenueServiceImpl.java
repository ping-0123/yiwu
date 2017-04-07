package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.DepartmentDao;
import com.yinzhiwu.springmvc3.dao.OrderDao;
import com.yinzhiwu.springmvc3.entity.Department;
import com.yinzhiwu.springmvc3.model.RevenueList;
import com.yinzhiwu.springmvc3.model.RevenueModel;
import com.yinzhiwu.springmvc3.service.RevenueService;

@Service
public class RevenueServiceImpl implements RevenueService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	@Qualifier("departmentDaoImplTwo")
	private DepartmentDao deptDao;
	
	public void getMonthlyRevenue2(int year, int month, int districtId, int productTypeId) {
		//取出该区域下所有的门店
		List<Department> storeList = deptDao.findStoresByDistrictId(districtId);
		int cols = storeList.size()+1;
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
		
	}

	@Override
	public List<RevenueList> getMonthlyRevenue(int year, int month, int districtId, int productTypeId) {
		
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
						 rm.setDate((Date)obj[2]);
						 rm.setAmount((Double)obj[3]);
						 obj =it.next();
					 }else{
						 rm.setStoreId(storeList.get(i).getId());
						 rm.setStoreName(storeList.get(i).getDeptName());
						 rm.setDate(ca.getTime());
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
