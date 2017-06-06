package com.yinzhiwu.springmvc3.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.springmvc3.dao.CapitalAccountDao;
import com.yinzhiwu.springmvc3.dao.CustomerYzwDao;
import com.yinzhiwu.springmvc3.dao.DepartmentYzwDao;
import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.dao.ExpGradeDao;
import com.yinzhiwu.springmvc3.dao.ExpRecordTypeDao;
import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.DepartmentYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.DistributerRegisterModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerRegisterApiView;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.service.ExpRecordService;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;



//@Transactional
@Service
public class DistributerServiceImpl extends BaseServiceImpl<Distributer, Integer> implements DistributerService {

	private static final Log LOG = LogFactory.getLog(DistributerServiceImpl.class);
	
	@Autowired
	private ExpGradeDao expGradeDao;
	
	
	@Autowired
	private DistributerDao distributerDao;
	
	@Autowired
	private DepartmentYzwDao departmentYzwDao;
	
	
	@Autowired
	private CustomerYzwDao customerYzwDao;
	
	@Autowired
	private ExpRecordTypeDao expRecordTypeDao;
	
	@Autowired
	private ExpRecordService expRecordService;
	
	@Autowired
	private MoneyRecordService moneyRecordService;
	
	@Autowired
	private CapitalAccountDao capitalAccountDao;
	
	@Autowired
	public void setBaseDao(DistributerDao distributerDao){
		super.setBaseDao(distributerDao);
	}
	
	private YiwuJson<DistributerApiView> mYiwuJson = new YiwuJson<DistributerApiView>(); 
	
	@Override
	public YiwuJson<DistributerApiView> register2(DistributerRegisterModel m) {
		Distributer d = new Distributer(m);
		return register(m.getInvitationCode(), d);
	}

	
	@Override
	public  YiwuJson<DistributerApiView> register(String invitationCode, Distributer distributer){
		//验证手机号码是否已注册
		if (distributerDao.findCountByPhoneNo(distributer.getPhoneNo()) > 0) 
			return new YiwuJson<>(distributer.getPhoneNo() + " 该手机号码已经被注册 ");
		//验证微信号是否已被注册
		if(distributerDao.findCountByWechatNo(distributer.getWechatNo())> 0)
			return new YiwuJson<>(distributer.getWechatNo() + " 该微信号已经被注册 ");
		
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
		
		//设置门店
		DepartmentYzw store = null;
		if(distributer.getFollowedByStore() != null){
			try {
				store= departmentYzwDao.get(distributer.getFollowedByStore().getId());
				distributer.setFollowedByStore(store);
			} catch (DataNotFoundException e) {
				LOG.warn(e.getMessage());
				distributer.setFollowedByStore(null);
			}
			
		}
		
		//关联Customer
		CustomerYzw customer;
		try {
			customer = customerYzwDao.findByPhoneNo(distributer.getPhoneNo());
		} catch (DataNotFoundException e) {
			try {
				customer = customerYzwDao.findByWeChat(distributer.getWechatNo());
			} catch (DataNotFoundException e1) {
				customer = new CustomerYzw(distributer);
//				customerYzwDao.save(customer);  
			}
		}
		distributer.setCustomer(customer);
		
		//注册成功
		try {
//			LOG.info("before save");
			distributerDao.saveBean(distributer);
//			LOG.info("after save");
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
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
	
		return new YiwuJson<>(wrapToApiView(distributer));
	
	}

	@Override
	public YiwuJson<DistributerApiView> loginByWechat(String wechatNo) {
		try {
			Distributer distributer = distributerDao.findByWechat(wechatNo);
			return new YiwuJson<>(wrapToApiView(distributer));
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
//		return mYiwuJson;
	}
	

	@Override
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password) {
		Distributer distributer;
		try {
			distributer = distributerDao.findByAccountPassword(account,password);
			return new YiwuJson<>(wrapToApiView(distributer));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
//		return mYiwuJson;
	}

	@Override
	public YiwuJson<DistributerApiView> findById(int id) {
		try{
			Distributer distributer = distributerDao.get(id);
			return new YiwuJson<>(wrapToApiView(distributer));
		}catch (DataNotFoundException e) {
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
	//		mYiwuJson.setData( wrapToApiView(distributer));
			return new YiwuJson<>(wrapToApiView(distributer));
		}catch(DataNotFoundException e){
			return new YiwuJson<>(e.getMessage());
		}
	}

	private DistributerApiView wrapToApiView(Distributer d){
		DistributerApiView distributerApiView = new DistributerApiView(d);
		distributerApiView.setBeatRate(distributerDao.getBeatRate(d.getExp()));
		return distributerApiView;
	}

//	@SuppressWarnings("unused")
	@Override
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId) {
		try{
			CapitalAccount defaultAccount = distributerDao.get(distributerId).getDefaultCapitalAccount();
			if(defaultAccount==null){
				return new YiwuJson<>("该用户尚未设置默认提现账户");
			}
			return new YiwuJson<>(new CapitalAccountApiView(defaultAccount));
	
		}catch (Exception e) {
			e.printStackTrace();
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
	public void setDefaultCapitalAccount(int distributerId, int accountId) {
		try{
			Distributer d = distributerDao.get(distributerId);
			CapitalAccount account = capitalAccountDao.get(accountId);
			d.setDefaultCapitalAccount(account);
			distributerDao.update(d);
		}catch (Exception e) {
			LOG.warn(e.getMessage());
		}
	}

	@Override
	public YiwuJson<Boolean> judgePhoneNoIsRegistered(String phoneNo) {
		if(distributerDao.findCountByPhoneNo(phoneNo) >0)
			return new YiwuJson<>(phoneNo + " 该手机号码已注册");
		return new YiwuJson<>(new Boolean(false));
	}

	@Override
	public YiwuJson<List<DistributerRegisterApiView>> findSubordiatesRegisterRecords(int distributerId) {
		try{
			Distributer distributer = distributerDao.get(distributerId);
			List<Distributer> subordiates = distributer.getSubordinates();
			if(subordiates.size()==0)
				return new YiwuJson<>(distributer.getName() +  "该分销者不存在下级客户");
			
			float exp = expRecordTypeDao.findSubordinateRegisterExpRecordType().getFactor();
			List<DistributerRegisterApiView> views = new ArrayList<>();
			for (Distributer d : subordiates) {
				views.add(new DistributerRegisterApiView(d,exp));
			}
			return new YiwuJson<>(views);
		}catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<List<DistributerRegisterApiView>> findSecondariesRegisterRecords(int distributerId) {
		try{
			Distributer distributer = distributerDao.get(distributerId);
			if(distributer == null)
				return new YiwuJson<>("no distributer found by id: " + distributerId);
			List<Distributer> subordiates = distributer.getSubordinates();
			if(subordiates.size()==0)
				return new YiwuJson<>(distributer.getName() +  "该分销者不存在下级客户");
			
			List<Distributer> secondaries = new ArrayList<>();
			for (Distributer s : subordiates) {
				secondaries.addAll(s.getSubordinates());
			}
			if(secondaries.size()==0)
				return new YiwuJson<>(distributer.getName() +  "该分销者不存在二级客户");
			
			float exp = expRecordTypeDao.findSecondaryRegisterExpRecordType().getFactor();
			List<DistributerRegisterApiView> views = new ArrayList<>();
			for (Distributer d : secondaries) {
				views.add(new DistributerRegisterApiView(d, exp));
			}
			
			return new YiwuJson<>(views);
		}catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}


	@Override
	public void modify(Integer id, Distributer entity)
			throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		super.modify(id, entity);
	}


	

}
