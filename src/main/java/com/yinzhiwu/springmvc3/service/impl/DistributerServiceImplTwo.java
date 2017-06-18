package com.yinzhiwu.springmvc3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yinzhiwu.springmvc3.dao.CustomerYzwDao;
import com.yinzhiwu.springmvc3.dao.DepartmentYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.RegisterEvent;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.DepartmentYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.DistributerApiView;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;

@Service
public class DistributerServiceImplTwo  extends DistributerServiceImpl implements DistributerService{

	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	private DepartmentYzwDao departmentYzwDao;
	
	@Autowired
	private CustomerYzwDao customerYzwDao;
	
	
	@Autowired
	private IncomeEventService incomeEventService;
	
	@Autowired
	private DistributerIncomeDao distributerIncomeDao;
	

	@Override
	public YiwuJson<DistributerApiView> register(String invitationCode, Distributer distributer) {
		
		/**
		 * init new distributer' default properties such as "createTime"
		 */
		distributer.init();
		
		/**
		 * verify that the phoneNo has been registered
		 */
		if (distributerDao.findCountByPhoneNo(distributer.getPhoneNo()) > 0) 
			return new YiwuJson<>(distributer.getPhoneNo() + " 该手机号码已经被注册 ");
		
		/**
		 * verify that the wechatNo has been registered
		 */
		if(distributerDao.findCountByWechatNo(distributer.getWechatNo())> 0)
			return new YiwuJson<>(distributer.getWechatNo() + " 该微信号已经被注册 ");
		
		/**
		 * set super proxy distributer
		 */
		if(StringUtils.hasLength(invitationCode))
			try {
				Distributer superDistributer = distributerDao.findByShareCode(invitationCode);
				distributer.setSuperDistributer(superDistributer);
			} catch (DataNotFoundException e) {
				logger.warn(e.getMessage());
				distributer.setSuperDistributer(null);
			}
		
		/**
		 * set followed store
		 */
		if(distributer.getFollowedByStore() != null)
			try {
				DepartmentYzw store= departmentYzwDao.get(distributer.getFollowedByStore().getId());
				distributer.setFollowedByStore(store);
			} catch (DataNotFoundException e) {
				logger.warn(e.getMessage());
				distributer.setFollowedByStore(null);
			}
		
		/**
		 * associate with customer
		 */
		CustomerYzw customer;
		try {
			customer = customerYzwDao.findByPhoneNo(distributer.getPhoneNo());
		} catch (DataNotFoundException e) {
			logger.info("no customer accociate with the distributer whose phoneNo is " +distributer.getPhoneNo());
			try {
				customer = customerYzwDao.findByWeChat(distributer.getWechatNo());
			} catch (DataNotFoundException e1) {
				logger.info("no customer accociate with the distributer whose wechatNo is " +distributer.getWechatNo());
				customer = new CustomerYzw(distributer);
				logger.info("new customer's name is " + customer.getName());
			}
		}
		distributer.setCustomer(customer);
		/**
		 * register to database
		 */
		distributerDao.save(distributer);
		/**
		 * produce register event
		 */
		RegisterEvent event = null;
		 if(distributer.getSuperDistributer() == null)
			 event = new RegisterEvent(
					 distributer, 
					 EventType.REGISTER_WITHOUT_INVATATION_CODE, 
					 1f);
		 else
			 event = new RegisterEvent(
					 distributer, 
					 EventType.REGISTER_WITH_INVATATION_CODE, 
					 1f, invitationCode);
		 incomeEventService.save(event);
		 /**
		  * get distributer's current exp income value and beat rate for return
		  */
		float expValue =0f;
		try {
			expValue = distributerIncomeDao.findCountByProperties(
					 new String[]{"distributer.id", "incomeType.id"}, 
					 new Object[]{distributer.getId(), IncomeType.EXP.getId()});
		} catch (Exception e) {
			logger.warn(e.getLocalizedMessage());
		}
		float beatRate = distributerIncomeDao.get_beat_rate(IncomeType.EXP,expValue);
		
		/*
		 * return dto
		 */
		try {
			return new YiwuJson<>(new DistributerApiView(distributerDao.get(distributer.getId()),beatRate));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	

	@Override
	public YiwuJson<DistributerApiView> findById(int id) {
		try{
			Distributer distributer = distributerDao.get(id);
			float rate = distributerIncomeDao.get_beat_rate(
					IncomeType.EXP,
					distributer.getDistributerIncome(IncomeType.EXP).getIncome());
			return new YiwuJson<>(new DistributerApiView(distributer, rate));
		}catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	
	
}
