package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.AppointmentYzwDao;
import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.LessonYzwDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.AfterAppointCheckInEvent;
import com.yinzhiwu.springmvc3.entity.income.CheckInEvent;
import com.yinzhiwu.springmvc3.entity.income.IncomeEvent;
import com.yinzhiwu.springmvc3.entity.income.WithoutAppointCheckInEvent;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.Contract;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CheckInSuccessApiView;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;

@Service
public class CheckInsYzwServiceImpl extends BaseServiceImpl<CheckInsYzw, Integer> implements CheckInsYzwService {

	@Autowired private CheckInsYzwDao checkInsYzwDao;
	@Autowired private OrderYzwDao orderDao;
	@Autowired private AppointmentYzwDao appointmentDao;
	@Autowired private IncomeEventService incomeEventService;
	@Autowired private DistributerDao distibuterDao;
	@Autowired private LessonYzwDao lessonDao;
	
	@Autowired
	public void setBaseDao(CheckInsYzwDao checkInsYzwDao)
	{
		super.setBaseDao(checkInsYzwDao);
	}

	@Override
	public int findCountByCustomerId(int customerId){
		return checkInsYzwDao.findCountByCustomerId(customerId);
	}
	
	@Override
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId) {
		List<String> contractNos = orderDao.find_contractNos_by_customer_id(customerId);
		if(contractNos.size() ==0)
			return new YiwuJson<>("客户"+ customerId + "尚未购买任何音之舞产品");
		List<LessonYzw> lessons = checkInsYzwDao.findByContractNos(contractNos);
		if(lessons == null || lessons.size() == 0)
			return new YiwuJson<>("没有上课记录");
		List<LessonApiView> views = new ArrayList<>();
		for (LessonYzw l : lessons) {
			views.add(new LessonApiView(l));
		}
		return new YiwuJson<>(views);
	}

	
	@Override
	public CheckInSuccessApiView saveCustomerCheckIn(int distributerId, int lessonId) throws Exception {
		Distributer distributer = distibuterDao.get(distributerId);
		if(distributer == null) throw new  Exception(distributerId + "用户不存在.");
		LessonYzw lesson = lessonDao.get(lessonId);
		if(lesson == null) throw new Exception(lessonId + "预约的课程不存在");
		CustomerYzw customer = distributer.getCustomer();
		if(customer == null) throw new Exception(distributer.getId() + "客户不存在");
		if("封闭式".equals(lesson.getCourseType())) throw new Exception("封闭式课程无须刷卡");
		if(!"开放式".equals(lesson.getCourseType())) throw new Exception("非开放式课程请在E5pc端按指纹刷卡");
		/**
		 * 判断是否已刷卡
		 */
		if(checkInsYzwDao.isCheckedIn(customer, lesson)) throw new Exception("已刷卡， 无须重复刷卡");
		Contract contract = orderDao.find_valid_contract_by_customer_by_subCourseType(
				customer.getId(), lesson.getSubCourseType());
		if(contract == null) throw new Exception("你没有购买音之舞相关产品， 不能刷卡");
		CheckInsYzw checkIn = new CheckInsYzw(customer.getMemberCard(), lesson, contract.getContractNo(), null);
		super.save(checkIn);
		/**
		 * 判断是否预约
		 */
		IncomeEvent event = null;
		if(appointmentDao.isAppointed(customer,lesson)){
			event = new AfterAppointCheckInEvent(distributer,  1f, checkIn);
		}else
			event = new WithoutAppointCheckInEvent(distributer,  1f, checkIn);
		incomeEventService.save(event);
		
		/*
		 * return 
		 */
		checkIn.setEvent((CheckInEvent) event);
		OrderYzw order = orderDao.findByContractNO(checkIn.getContractNo());
		return new CheckInSuccessApiView(checkIn.getEvent(), order.getContract());
	}
	
	
	
	
}
