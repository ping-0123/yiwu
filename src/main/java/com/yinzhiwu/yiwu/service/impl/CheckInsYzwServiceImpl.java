package com.yinzhiwu.yiwu.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.AppointmentYzwDao;
import com.yinzhiwu.yiwu.dao.CheckInsYzwDao;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.LessonYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.CheckInAfterAppointEvent;
import com.yinzhiwu.yiwu.entity.income.CheckInEvent;
import com.yinzhiwu.yiwu.entity.income.CheckInWithoutAppointEvent;
import com.yinzhiwu.yiwu.entity.income.IncomeEvent;
import com.yinzhiwu.yiwu.entity.yzw.CheckInsYzw;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CheckInSuccessApiView;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.service.CheckInsYzwService;
import com.yinzhiwu.yiwu.service.IncomeEventService;

@Service
public class CheckInsYzwServiceImpl extends BaseServiceImpl<CheckInsYzw, Integer> implements CheckInsYzwService {

	@Autowired
	private CheckInsYzwDao checkInsYzwDao;
	@Autowired
	private OrderYzwDao orderDao;
	@Autowired
	private AppointmentYzwDao appointmentDao;
	@Autowired
	private IncomeEventService incomeEventService;
	@Autowired
	private DistributerDao distibuterDao;
	@Autowired
	private LessonYzwDao lessonDao;
	@Autowired
	private CustomerYzwDao customerDao;
	
	@Autowired
	public void setBaseDao(CheckInsYzwDao checkInsYzwDao) {
		super.setBaseDao(checkInsYzwDao);
	}

	@Override
	public int findCountByCustomerId(int customerId) {
		return checkInsYzwDao.findCountByCustomerId(customerId);
	}

	@Override
	public int findCheckedInLessonsCountOfCustomer(int customerId) {
		String memberCard = customerDao.get(customerId).getMemberCard();
		return checkInsYzwDao.findCheckedInLessonsCountByMemeberCard(memberCard);
	}
	
	@Override
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId) {
		String memberCard = customerDao.get(customerId).getMemberCard();
		List<LessonApiView> lessonApiViews = checkInsYzwDao.findLessonApiViewsByMemeberCard(memberCard);
		return YiwuJson.createBySuccess(lessonApiViews);
	}

	@Override
	public PageBean<LessonApiView> findPageViewByCustomer(int customerId, Integer pageNo, Integer pageSize) {
		String memberCard = customerDao.get(customerId).getMemberCard();
		return checkInsYzwDao.findPageCheckedInLessonApiViewsByMemberCard(memberCard, pageNo, pageSize);

	}

	@Override
	public CheckInSuccessApiView saveCustomerCheckIn(int distributerId, int lessonId)
			throws YiwuException, DataNotFoundException {
		
		boolean isAppointed = false;
		Distributer distributer = distibuterDao.get(distributerId);
		if (distributer == null)
			throw new YiwuException(distributerId + "用户不存在.");
		LessonYzw lesson = lessonDao.get(lessonId);
		if (lesson == null)
			throw new YiwuException(lessonId + "预约的课程不存在");
		CustomerYzw customer = distributer.getCustomer();
		if (customer == null)
			throw new YiwuException(distributer.getId() + "客户不存在");
		if (CourseType.CLOSED== lesson.getCourseType())
			throw new YiwuException("封闭式课程无须刷卡");

		//判断是否已经预约
//		if(! appointmentDao.isAppointed(customer, lesson))
//			throw new YiwuException("未预约不能刷卡上课");
		//判断是否已刷卡
		if (checkInsYzwDao.isCheckedIn(customer.getMemberCard(), lesson.getId()))
			throw new YiwuException("已刷卡， 无须重复刷卡");
		//上课时间是否已已过
		Calendar end = Calendar.getInstance();
		end.setTime(lesson.getStartDateTime());
		end.add(Calendar.MINUTE, lesson.getLessonMinutes());
		if(Calendar.getInstance().after(end))
			throw new YiwuException("课程已结束");
		//获取已经预约的会籍合约
		String contractNo = appointmentDao.getAppointedContractNo(distributer.getId(), lesson.getId());
		if(contractNo != null)
			isAppointed = true;
		else{
			//判断是否能刷卡
			Contract contract = orderDao.findCheckedContractByCustomerIdAndSubCourseType(
					customer.getId(),
					lesson.getSubCourseType());
			if (contract == null)
				throw new YiwuException("你没有购买音之舞\"" + lesson.getSubCourseType().getName()+  "\"类舞蹈卡,不能刷卡");
			contractNo = contract.getContractNo();
		}
			
		
		// 刷卡
		CheckInsYzw checkIn = new CheckInsYzw(distributer, lesson, contractNo);
		checkInsYzwDao.save(checkIn);
		/**
		 * 判断是否预约, 并保存刷卡事件
		 */
		IncomeEvent event = null;
		if (isAppointed) {
			event = new CheckInAfterAppointEvent(distributer, checkIn);
		} else{
			event = new CheckInWithoutAppointEvent(distributer,checkIn);
			orderDao.updateContractWithHoldTimes(contractNo, 1);
		}
		incomeEventService.save(event);
		
		
		return new CheckInSuccessApiView((CheckInEvent) event, orderDao.findContractByContractNo(contractNo));
	}



}
