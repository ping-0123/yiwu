package com.yinzhiwu.yiwu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.dao.LessonCheckInYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Distributer.Role;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.event.RegisterEvent;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;

@Service
public class DistributerServiceImpl extends BaseServiceImpl<Distributer, Integer> implements DistributerService {
	@Autowired
	public void setDao(DistributerDao dao) {
		super.setBaseDao(dao);
	}

	@Autowired private ApplicationContext applicationContext;
	
	@Autowired private DistributerDao distributerDao;
	@Autowired private DepartmentYzwDao departmentYzwDao;
	@Autowired private CustomerYzwDao customerDao;
	@Autowired private DistributerIncomeDao distributerIncomeDao;
	@Autowired private CapitalAccountDao capitalAccountDao;
	@Autowired private EmployeeYzwDao employeeDao;
	@Autowired private EmployeeDepartmentYzwDao empDeptDao;
	@Qualifier("fileServiceImpl") @Autowired private FileService fileService;
	@Autowired private LessonCheckInYzwDao checkInsDao;
	@Autowired private OrderYzwDao orderDao;
	
	@Value("${system.headIcon.savePath}")
	private String headIconSavePath;
	@Value("${system.headIcon.url}")
	private String headIconUrl;

	
	@Override
	public Distributer doRegister(String mobileNumber, String openId, String memberCard, String invitationCode) throws YiwuException
	{
		
		Distributer distributer = new Distributer();
		distributer.setPhoneNo(mobileNumber);
		distributer.setWechatNo(openId);
		
		// set distributer role;
		EmployeeYzw company = employeeDao.findByTel(mobileNumber);
		EmployeeYzw employee = null;
		try {
			employee = employeeDao.findByPhoneNo(mobileNumber);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		if(company !=null){
			distributer.setRole(Role.COMPANY);
			distributer.setSuperDistributer(null);
			distributer.setServer(company);
			distributer.setFollowedByStore(empDeptDao.findOneDepartmentByEmployee(company.getId()));
			distributer.setDepartment(distributer.getFollowedByStore());
			distributer.setUser(company);
		}else if (null != employee) {
			distributer.setRole(Role.EMPLOYEE);
			distributer.setEmployee(employee);
			distributer.setName(employee.getName());
			//自我服务
			distributer.setServer(employee);
			//公司员工不能有上级归属
			distributer.setSuperDistributer(null);
		}else {
			distributer.setRole(Role.CUSTOMER);
			if(StringUtils.hasLength(invitationCode)){
				Distributer super1 = distributerDao.findByShareCode(invitationCode);
				distributer.setSuperDistributer(super1);
				EmployeeYzw server = super1.getEmployee();
				if(server == null)
					server = super1.getServer();
				if(server != null){
					distributer.setServer(server);
					distributer.setFollowedByStore(empDeptDao.findOneDepartmentByEmployee(server.getId()));
				}
				
			}
		}
		
		//set distributer customer
		if(null != memberCard){
			CustomerYzw customer = null;
			try {
				customer = customerDao.getByMemberCard(memberCard);
			} catch (DataNotFoundException e) {
				throw new YiwuException("输入的会员卡号在系统中不存在");
			}
			distributer.setCustomer(customer);
			distributer.setMemberCard(memberCard);
			
			distributer.setServer(customer.getSalesman());
			distributer.setBirthday(customer.getBirthday());
			distributer.setName(customer.getName());
			distributer.setCustomerAgeType(customer.getCustomerAgeType());
			distributer.setGender(customer.getGender());
		}else{
			CustomerYzw customer;
			customer = new CustomerYzw();
			customer.setSalesman(distributer.getServer());
			customer.setMobilePhone(mobileNumber);
			customer.init();
			
			distributer.setCustomer(customer);
		}
		
		//保存
		save(distributer);
		
		//推送事件
		applicationContext.publishEvent(new RegisterEvent(distributer));
		
		return distributer;
	}
	
	@SuppressWarnings("unused")
	private CustomerYzw _matchCustomer(DistributerRegisterModel registerModel) {
		CustomerYzw customer = customerDao.findByPhoneByWechat(registerModel.getPhoneNo(), registerModel.getWechatNo());
		if(customer == null){
			try {
				customer = customerDao.findByPhoneNo(registerModel.getPhoneNo());
			} catch (DataNotFoundException e) {
				customer = customerDao.findByWeChat(registerModel.getWechatNo());
			}
		}
		return customer;
	}



	
	
	@Override
	public float getExpWinRate(Distributer distributer){
		return distributerIncomeDao.calculateBeatRatio(IncomeType.EXP	, distributer.getIncomeValue(IncomeType.EXP));
	}
	

	
	
	@Override
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId) {
		try {
			CapitalAccount defaultAccount = distributerDao.get(distributerId).getDefaultCapitalAccount();
			if (defaultAccount == null) {
				return new YiwuJson<>("该用户尚未设置默认提现账户");
			}
//			return new YiwuJson<>(new CapitalAccountApiView(defaultAccount));
			return null;
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId, String typeName) {
		YiwuJson<CapitalAccountApiView> yiwuJson = new YiwuJson<>();
		Distributer d;
		try {
			d = distributerDao.get(distributerId);
		} catch (DataNotFoundException e) {
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		Set<CapitalAccount> accounts = d.getCapitalAccounts();
		for (CapitalAccount a : accounts) {
			if (typeName.equals(a.getPaymentMode().toString())) {
//				yiwuJson.setData(new CapitalAccountApiView(a));
				break;
			}
		}
		return yiwuJson;
	}

	@Override
	public void setDefaultCapitalAccount(int distributerId, int accountId) throws Exception {
		Distributer distributer = distributerDao.get(distributerId);
		CapitalAccount account = capitalAccountDao.get(accountId);
		if (!account.getDistributer().equals(distributer))
			throw new Exception("帐号" + accountId + "不属于" + distributerId + ",不能设置为其默认提现帐号");
		distributer.setDefaultCapitalAccount(account);
		distributerDao.update(distributer);
	}


	@Override
	public YiwuJson<DistributerModifyModel> modify(int distributerId, DistributerModifyModel model) {
		Distributer distributer = model.toDistributer();
		String fileName = null;
		if (model.getImage() != null && model.getImage().getSize() > 0) {
			try {
				fileName = fileService.upload(model.getImage());
				distributer.setHeadIconName(fileName);
			} catch (IllegalStateException e) {
				logger.error(e.getMessage(),e);
				return new YiwuJson<>("服务器内部原因，头像保存失败");
			} catch (IOException e) {
				logger.error(e.getMessage());
				return new YiwuJson<>("图片保存目录 " + headIconSavePath + " 不存在");
			}
		}

		try {
			super.modify(distributerId, distributer);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return new YiwuJson<>("服务器内部原因， 修改会员资料失败");
		}

		model.setImage(null);
		model.setImageUrl(fileService.getFileUrl(fileName));
		return new YiwuJson<>(model);
	}

	@Override
	public String getHeadIconSavePath() {
		return headIconSavePath;
	}

	@Override
	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconSavePath(String headIconSavePath) {
		this.headIconSavePath = headIconSavePath;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}




	@Override
	public PageBean<CustomerDto> findVisableDistributersByEmployee(int distributerId, String key,  int pageNo,
			int pageSize) throws YiwuException {
		//必须,找出自己的employeeId
		Distributer distributer;
		try {
			distributer = distributerDao.get(distributerId);
		} catch (DataNotFoundException e) {
			throw new RuntimeException(e);
		}
		if(distributer == null) throw new YiwuException("无效的distributerId: " + distributerId);
		EmployeeYzw employee = distributer.getEmployee();
		if(employee == null ) throw new YiwuException(distributerId + " 非内部员工, 不允许查询");
		Integer employeeId = employee.getId();
		
		//1. 找出我管辖的门店 
		List<Integer> storeIds = new ArrayList<>();
		storeIds = departmentYzwDao.findStoreIdsByEmplyee(employeeId);
		
		//2. 找出下属的员工(含自己)
		List<Integer> employeeIds = new ArrayList<>();
		employeeIds = empDeptDao.findEmployeesUnderDepts(storeIds);
		
		//3. 所管辖的员工的distributerId;  (含自己)
		List<Integer> distributerIds = new ArrayList<>();
		distributerIds = distributerDao.findIdsByemployees(employeeIds);
		
		PageBean<CustomerDto> page = distributerDao.findDtoPageByDistributerByKey(storeIds, employeeIds, distributerIds, key, pageNo, pageSize);
		return page;
	}

	@Override
	public Distributer findByWechatNo(String wechatNo) throws DataNotFoundException {
		return distributerDao.findByWechat(wechatNo);
	}

	@Override
	public YiwuJson<StoreApiView> findDefaultStoreApiView(Integer distributerId) {
		Distributer distributer;
		try {
			distributer = distributerDao.get(distributerId);
		} catch (DataNotFoundException e) {
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
		
		StoreApiView view = checkInsDao.findStoreApiViewOfLastCheckedOpenLesson(distributer.getMemberCard());
		if(view == null){
			view = orderDao.findStoreOfValidOpenContractOrder(distributer.getCustomer().getId());
		}
		if(view == null)
			return YiwuJson.createByErrorMessage("");
		return YiwuJson.createBySuccess(view);
	}

	@Override
	public Distributer findbyCustomer(CustomerYzw customer) throws DataNotFoundException {
		return distributerDao.findByCustomerId(customer.getId());
	}

	@Override
	public Distributer findByPhoneNo(String mobileNumber) throws DataNotFoundException {
		return distributerDao.findByPhoneNo(mobileNumber);
	}

	
	@Override
	public boolean validateMobileNumberBeforeRegister(String mobileNumber) {
		try {
			return null == distributerDao.findByPhoneNo(mobileNumber);
		} catch (DataNotFoundException e) {
			return true;
		}
	}

	@Override
	public boolean validateOpenIdBeforeRegister(String openId) {
		try {
			return null == distributerDao.findByWechat(openId);
		} catch (DataNotFoundException e) {
			return true;
		}
	}

	@Override
	public boolean validateMembercardBeforeRegister(String memberCard) {
		try {
			return null == distributerDao.findByMemberCard(memberCard);
		} catch (DataNotFoundException e) {
			return true;
		}
	}

	@Override
	public Distributer findByMemberCard(String memberCard) throws DataNotFoundException {
		return distributerDao.findByMemberCard(memberCard);
	}

}
