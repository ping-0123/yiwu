package com.yinzhiwu.springmvc3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CheckInsYzwDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.LessonApiView;
import com.yinzhiwu.springmvc3.service.CheckInsYzwService;
import com.yinzhiwu.springmvc3.service.OrderYzwService;

@Service
public class CheckInsYzwServiceImpl extends BaseServiceImpl<CheckInsYzw, Integer> implements CheckInsYzwService {

	@Autowired
	private CheckInsYzwDao checkInsYzwDao;
	
	@Autowired
	private OrderYzwDao orderDao;
	
	@Autowired
	private OrderYzwService orderService;
	
	@Autowired
	public void setBaseDao(CheckInsYzwDao checkInsYzwDao)
	{
		super.setBaseDao(checkInsYzwDao);
	}

	@Override
	public YiwuJson<Integer> findCountByCustomerId(int customerId) {
		List<String> contractNos = orderService.findContractNosByCustomerId(customerId);
		int count = checkInsYzwDao.findCountByContractNos(contractNos);
		return new YiwuJson<>(new Integer(count));
	}

	@Override
	public YiwuJson<List<LessonApiView>> findByCustomerId(int customerId) {
		List<String> contractNos = orderService.findContractNosByCustomerId(customerId);
		List<LessonYzw> lessons = checkInsYzwDao.findByContractNos(contractNos);
		if(lessons == null || lessons.size() == 0)
			return new YiwuJson<>("没有上课记录");
		List<LessonApiView> views = new ArrayList<>();
		for (LessonYzw l : lessons) {
			views.add(new LessonApiView(l));
		}
		return new YiwuJson<>(views);
	}
	
	
}
