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
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.service.DistributerService;

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
	public void setBaseDao(DistributerDao distributerDao){
		super.setBaseDao(distributerDao);
	}
	
	@Override
	public  YiwuJson<Distributer> register(String invitationCode, Distributer distributer){
		YiwuJson<Distributer> yiwuJson = new YiwuJson<>();
		
		//设置经验等级为初始等级
		distributer.setExpGrade(expGradeDao.findLowestGrade());
		
		//设置上级代理
		Distributer superDistributer = null;
		if(invitationCode != null)
			try {
				superDistributer = distributerDao.findByShareCode(invitationCode);
				distributer.setSuperDistributer(superDistributer);
			} catch (DataNotFoundException e) {
				yiwuJson.setMsg("无效的分享码");
			}
		
		//关联Customer
		Customer customer;
		try {
			customer = customerDao.findByPhoneNo(distributer.getPhoneNo());
		} catch (DataNotFoundException e) {
			customer = customerDao.findByWeChat(distributer.getWechatNo());
		}
		distributer.setCustomer(customer);
		
		//注册成功
		distributerDao.save(distributer);
		
		//注册产生exp收益
		//// 上级代理的
		if(superDistributer != null) {
			
		}
		//// 本人的
		
		return null;
	}
	
}
