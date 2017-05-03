package com.yinzhiwu.springmvc3.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.springmvc3.dao.CustomerDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.ExpGradeDao;
import com.yinzhiwu.springmvc3.entity.Customer;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.DistributerApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.service.ExpRecordService;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;
import com.yinzhiwu.springmvc3.util.GeneratorUtil;
import com.yinzhiwu.springmvc3.util.SecurityUtil;
import com.yinzhiwu.springmvc3.util.ShareCodeUtil;



@Service
public class DistributerServiceImpl extends BaseServiceImpl<Distributer, Integer> implements DistributerService {

	private static final Log logger = LogFactory.getLog(DistributerServiceImpl.class);
	
	@Autowired
	private ExpGradeDao expGradeDao;
	
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ExpRecordService expRecordService;
	
	@Autowired
	private MoneyRecordService moneyRecordService;
	
	@Autowired
	public void setBaseDao(DistributerDao distributerDao){
		super.setBaseDao(distributerDao);
	}
	
	private YiwuJson<DistributerApiView> mYiwuJson = new YiwuJson<DistributerApiView>(); 
	
	
	
	@Override
	public  YiwuJson<DistributerApiView> register(String invitationCode, Distributer distributer){
		
		//设置默认帐号
		distributer.initialize();
		
		//设置经验等级为初始等级
		distributer.setExpGrade(expGradeDao.findLowestGrade());
		
		//设置上级代理
		Distributer superDistributer = null;
		if(invitationCode != null)
			try {
				superDistributer = distributerDao.findByShareCode(invitationCode);
				distributer.setSuperDistributer(superDistributer);
			} catch (DataNotFoundException e) {
				mYiwuJson.setMsg("无效的分享码");
			}
		
		
		//关联Customer
		Customer customer;
		try {
			customer = customerDao.findByPhoneNo(distributer.getPhoneNo());
		} catch (DataNotFoundException e) {
			try {
				customer = customerDao.findByWeChat(distributer.getWechatNo());
			} catch (DataNotFoundException e1) {
				customer = new Customer(distributer);
			}
		}
		distributer.setCustomer(customer);
		
		//注册成功
		try {
			distributerDao.saveBean(distributer);
		} catch (Exception e) {
			mYiwuJson.setMsg(e.getMessage());
			mYiwuJson.setResult(false);
			return mYiwuJson;
		}
		
		//注册产生收益
		if(superDistributer != null) {
		//// 上级代理的exp收益
			expRecordService.saveSubordinateRegisterExpRecord(superDistributer, distributer);
		//注册产生上级基金收益基金收益
			moneyRecordService.saveRegisterFundsRecord(superDistributer, distributer);
		//// 上上级代理的exp收益
			if(superDistributer.getSuperDistributer() != null)
				expRecordService.saveSecondaryRegisterExpRecord(
						superDistributer.getSuperDistributer(),
						distributer);
		}
	
		
		mYiwuJson.setData(wrapToApiView(distributer));
		return mYiwuJson;
	}

	@Override
	public YiwuJson<DistributerApiView> loginByWechat(String wechatNo) {
		try {
			Distributer distributer = distributerDao.findByWechat(wechatNo);
			mYiwuJson.setData(wrapToApiView(distributer));
		} catch (DataNotFoundException e) {
			mYiwuJson.setMsg(e.getMessage());
			mYiwuJson.setResult(false);
			return mYiwuJson;
		}
		return mYiwuJson;
	}
	
	private DistributerApiView wrapToApiView(Distributer d){
		DistributerApiView distributerApiView = new DistributerApiView(d);
		distributerApiView.setBeatRate(distributerDao.getBeatRate(d.getExp()));
		return distributerApiView;
	}

	@Override
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password) {
		Distributer distributer;
		try {
			distributer = distributerDao.findByAccountPassword(account,password);
			mYiwuJson.setData(wrapToApiView(distributer));
		} catch (Exception e) {
			mYiwuJson.setMsg(e.getMessage());
			mYiwuJson.setResult(false);
			return mYiwuJson;
		}
		return mYiwuJson;
	}

	@Override
	public YiwuJson<DistributerApiView> findById(int id) {
		Distributer distributer = distributerDao.get(id);
		mYiwuJson.setData(wrapToApiView(distributer));
		return mYiwuJson;
	}

	
}
