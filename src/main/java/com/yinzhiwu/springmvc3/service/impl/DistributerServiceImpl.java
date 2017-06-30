package com.yinzhiwu.springmvc3.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.CustomerYzwDao;
import com.yinzhiwu.springmvc3.dao.DepartmentYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.DistributerIncomeDao;
import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.dao.ShareTweetEventDao;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.income.DistributerIncome;
import com.yinzhiwu.springmvc3.entity.income.RegisterEvent;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.DepartmentYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.DistributerRegisterModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerApiView;
import com.yinzhiwu.springmvc3.model.view.TopThreeApiView;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.service.IncomeEventService;
import com.yinzhiwu.springmvc3.util.UrlUtil;

@Service
public class DistributerServiceImpl extends BaseServiceImpl<Distributer, Integer> implements DistributerService{

	@Autowired private DistributerDao distributerDao;
	@Autowired private DepartmentYzwDao departmentYzwDao;
	@Autowired private CustomerYzwDao customerYzwDao;
	@Autowired private IncomeEventService incomeEventService;
	@Autowired private DistributerIncomeDao distributerIncomeDao;
	@Autowired private ShareTweetEventDao shareTweeetEventDao;
	@Autowired private OrderYzwDao orderDao;
	@Autowired private CapitalAccountDao capitalAccountDao;
	

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
	@Override
	public YiwuJson<DistributerApiView> loginByWechat(String wechatNo) {
		try {
			Distributer distributer = distributerDao.findByWechat(wechatNo);
			float rate = distributerIncomeDao.get_beat_rate(
					IncomeType.EXP,
					distributer.getDistributerIncome(IncomeType.EXP).getIncome());
			return new YiwuJson<>(new DistributerApiView(distributer, rate));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	@Override
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password) {
		try {
			Distributer distributer = distributerDao.findByAccountPassword(account, password);
			float rate = distributerIncomeDao.get_beat_rate(
					IncomeType.EXP,
					distributer.getDistributerIncome(IncomeType.EXP).getIncome());
			return new YiwuJson<>(new DistributerApiView(distributer, rate));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	@Override
	public YiwuJson<DistributerApiView> modifyHeadIcon(int id, MultipartFile multipartFile, String fileSavePath) {
		try{
			Distributer distributer = distributerDao.get(id);
			String imageName = distributer.getMemberId() + ".jpg";
			File imageFile = new File(fileSavePath, imageName);
			try {
				multipartFile.transferTo(imageFile);
			} catch (IllegalStateException | IOException e) {
				return new YiwuJson<>(e.getMessage());
			}
			distributer.setHeadIconName(imageName);
			distributerDao.update(distributer);
			float rate = distributerIncomeDao.get_beat_rate(
					IncomeType.EXP,
					distributer.getDistributerIncome(IncomeType.EXP).getIncome());
			return new YiwuJson<>(new DistributerApiView(distributer, rate));
		}catch(DataNotFoundException e){
			return new YiwuJson<>(e.getMessage());
		}
	}
	@Override
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId) {
		try{
			CapitalAccount defaultAccount = distributerDao.get(distributerId).getDefaultCapitalAccount();
			if(defaultAccount==null){
				return new YiwuJson<>("该用户尚未设置默认提现账户");
			}
			return new YiwuJson<>(new CapitalAccountApiView(defaultAccount));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@Override
	public YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId, String typeName) {
		try{
			YiwuJson<CapitalAccountApiView> yiwuJson = new YiwuJson<>();
			Distributer d = distributerDao.get(distributerId);
			Set<CapitalAccount> accounts = d.getCapitalAccounts();
			for (CapitalAccount a : accounts) {
				if(typeName.equals(a.getCapitalAccountType().getName())){
					yiwuJson.setData(new CapitalAccountApiView(a));
					break;
				}
			}
			return yiwuJson;
		}catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	@Override
	public void setDefaultCapitalAccount(int distributerId, int accountId) throws Exception {
			Distributer distributer = distributerDao.get(distributerId);
			CapitalAccount account = capitalAccountDao.get(accountId);
			if(!account.getDistributer().equals(distributer)) 
				throw  new Exception("帐号" + accountId + "不属于" + distributerId + ",不能设置为其默认提现帐号");
			distributer.setDefaultCapitalAccount(account);
			distributerDao.update(distributer);
	}
	@Override
	public YiwuJson<Boolean> judgePhoneNoIsRegistered(String phoneNo) {
		if(distributerDao.findCountByPhoneNo(phoneNo) >0){
			YiwuJson<Boolean> yiwuJson = new YiwuJson<>(new Boolean(true));
			yiwuJson.setMsg(phoneNo + " 该手机号码已注册");
			return yiwuJson;
		}
		return new YiwuJson<>(new Boolean(false));
	}
	@Override
	public YiwuJson<DistributerApiView> register2(DistributerRegisterModel m) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TopThreeApiView> getBrokerageTopThree() {
		List<DistributerIncome> distributerIncomes =  distributerIncomeDao.getTopN(IncomeType.BROKERAGE.getId(), 3);
		logger.debug(distributerIncomes==null?0:distributerIncomes.size());
		List<TopThreeApiView> views = new ArrayList<>();
		for (DistributerIncome income : distributerIncomes) {
			TopThreeApiView v = new TopThreeApiView();
			try{
				v.setDistributerName(income.getDistributer().getName());
				v.setRegisterDate(income.getDistributer().getRegistedTime());
				v.setSumBrokerageIncome(income.getAccumulativeIncome());
				v.setSumShareTweetTimes(shareTweeetEventDao.findShareTweetTimes(income.getDistributer().getId()));
				v.setSumMemberCount(distributerDao.findCountByProperty("superDistributer.id", income.getDistributer().getId()));
				v.setSumOrderCount(orderDao.findCountByProperty("customer.id", income.getDistributer().getCustomer().getId()));
				v.setHeadIconUrl(UrlUtil.toHeadIcomUrl(income.getDistributer().getHeadIconName()));
			}catch (Exception e) {
				logger.error(e);
			}
			views.add(v);
		}
		return views;
	}



	
}
