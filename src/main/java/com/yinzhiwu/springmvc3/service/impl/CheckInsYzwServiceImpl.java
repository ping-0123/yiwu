package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.dao.LessonYzwDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.entity.yzwOld.Customer;
import com.yinzhiwu.springmvc3.entity.yzwOld.Lesson;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;

@Service
public class CheckInsYzwServiceImpl extends BaseServiceImpl<CheckInsYzw, Integer> implements CheckInsYzwService {

	@Autowired
	private CheckInsYzwDao checkInsYzwDao;
	
	@Autowired
	private OrderYzwDao orderDao;
	
	@Autowired
	private LessonYzwDao lessonDao;
	
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
	public void saveByCutomerByLesson(int customerId, int lessonId) throws DataNotFoundException,Exception {
		LessonYzw lesson = lessonDao.get(lessonId);
		if("封闭式".equals(lesson.getCourseType())) throw new Exception("封闭式课程无须刷卡");
		if(!"开放式".equals(lesson.getCourseType())) throw new Exception("非开放式课程请在E5pc端按指纹刷卡");
		/**
		 * 判断是否预约
		 */
		List<OrderYzw> validOrders = orderDao.find_valid_orders_by_customer_by_subCourseType(
				customerId, lesson.getSubCourseType());
		//未预约不给经验值
		//预约上课刷卡给20经验值
		
	}
	
	
}
