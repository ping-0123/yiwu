package com.yinzhiwu.yiwu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.dao.CustomerYzwDao;
import com.yinzhiwu.yiwu.dao.DepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.dao.DistributerIncomeDao;
import com.yinzhiwu.yiwu.dao.EmployeeDepartmentYzwDao;
import com.yinzhiwu.yiwu.dao.EmployeeYzwDao;
import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.dao.ShareTweetEventDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.income.DistributerIncome;
import com.yinzhiwu.yiwu.entity.income.RegisterEvent;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.entity.yzw.EmployeeYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;
import com.yinzhiwu.yiwu.model.view.TopThreeApiView;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.IncomeEventService;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.EmpDistributerDto;

@Service
public class DistributerServiceImpl extends BaseServiceImpl<Distributer, Integer> implements DistributerService {
	@Autowired
	public void setDao(DistributerDao dao) {
		super.setBaseDao(dao);
	}

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
	@Autowired
	private ShareTweetEventDao shareTweeetEventDao;
	@Autowired
	private OrderYzwDao orderDao;
	@Autowired
	private CapitalAccountDao capitalAccountDao;
	@Autowired private EmployeeYzwDao employeeDao;
	@Autowired private EmployeeDepartmentYzwDao empDeptDao;
	@Autowired private FileService fileService;
//	@Autowired private EmployeePostYzwDao empPostDao;
	
	@Value("${system.headIcon.savePath}")
	private String headIconSavePath;
	@Value("${system.headIcon.url}")
	private String headIconUrl;

	@Override
	public YiwuJson<DistributerRegisterModel> register(DistributerRegisterModel registerModel) {
		if(registerModel == null) throw new IllegalArgumentException("入参不能为null");
		Distributer distributer = registerModel.toDistributer();
		String message = null;
		String invitationCode = registerModel.getInvitationCode();
		
		/**
		 * init new distributer' default properties such as "createTime"
		 */
		distributer.init();

		/**
		 * verify that the phoneNo has been registered
		 */
		if (distributerDao.findCountByPhoneNo(registerModel.getPhoneNo()) > 0)
			return new YiwuJson<>(distributer.getPhoneNo() + " 该手机号码已经被注册 ");

		/**
		 * verify that the wechatNo has been registered
		 */
		if (distributerDao.findCountByWechatNo(registerModel.getWechatNo()) > 0)
			return new YiwuJson<>(distributer.getWechatNo() + " 该微信号已经被注册 ");

		/**
		 * set super proxy distributer
		 */
		if (StringUtils.hasLength(invitationCode)){
			Distributer superDistributer = distributerDao.findByShareCode(invitationCode);
			if(superDistributer != null){
				distributer.setSuperDistributer(superDistributer);
				distributer.setServer(superDistributer.getEmployee());
			}else
				message = "无效的邀请码:" + invitationCode;
		}

		/**
		 * set followed store
		 */
		if (registerModel.getFollowedByStoreId() != null){
				DepartmentYzw store = departmentYzwDao.get(registerModel.getFollowedByStoreId());
				distributer.setFollowedByStore(store);
		}
		
		/**
		 * associate with employee 
		 */
		EmployeeYzw emp =null;
		try {
			emp = employeeDao.findByPhoneNo(registerModel.getPhoneNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(emp != null){
			distributer.setEmployee(emp);
			distributer.setName(emp.getName());
		}
			

		/**
		 * associate with customer
		 */
		CustomerYzw customer = customerYzwDao.findByPhoneNo(distributer.getPhoneNo());
		if(customer == null)
			customer = customerYzwDao.findByWeChat(distributer.getWechatNo());
		if(customer != null){
			distributer.setBirthday(customer.getBirthday());
			distributer.setMemberId(customer.getMemberCard());
			distributer.setName(customer.getName());
			distributer.setCustomerType(customer.getCustomerAgeType());
			if(distributer.getGender() == null)
				distributer.setGender(customer.getGender());
			EmployeeYzw server = customer.getSalesman();
			if(server != null && !server.getRemoved()){
				distributer.setServer(server);
				DepartmentYzw dept = server.getDepartment();
				if(dept != null && dept.getName() != null && dept.getName().endsWith("店"));
					distributer.setFollowedByStore(dept);
			}
		}else {
			customer = new CustomerYzw(distributer);
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
		if (distributer.getSuperDistributer() == null)
			event = new RegisterEvent(distributer, EventType.REGISTER_WITHOUT_INVATATION_CODE, 1f);
		else
			event = new RegisterEvent(distributer, EventType.REGISTER_WITH_INVATATION_CODE, 1f, invitationCode);
		incomeEventService.save(event);
		/**
		 * get distributer's current exp income value and beat rate for return
		 */
		// float expValue =0f;
		// try {
		// expValue = distributerIncomeDao.findCountByProperties(
		// new String[]{"distributer.id", "incomeType.id"},
		// new Object[]{distributer.getId(), IncomeType.EXP.getId()});
		// } catch (Exception e) {
		// logger.warn(e.getLocalizedMessage());
		// }
		//// float beatRate =
		// distributerIncomeDao.get_beat_rate(IncomeType.EXP,expValue);

		/*
		 * return dto
		 */
		YiwuJson<DistributerRegisterModel> yiwu = new YiwuJson<>(registerModel);
		yiwu.setMsg(message);
		return yiwu;
	}

	@Override
	public YiwuJson<DistributerApiView> findById(int id) {
		Distributer distributer = distributerDao.get(id);
		DistributerApiView view = _wrapDaoToApiView(distributer);
		return new YiwuJson<>(view);
	}

	@Override
	public YiwuJson<DistributerApiView> loginByWechat(String wechatNo) {
		try {
			Distributer distributer = distributerDao.findByWechat(wechatNo);
			DistributerApiView view = _wrapDaoToApiView(distributer);
			return new YiwuJson<>(view);

		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password) {
		try {
			Distributer distributer = distributerDao.findByAccountPassword(account, password);
			DistributerApiView view = _wrapDaoToApiView(distributer);
			return new YiwuJson<>(view);
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	private DistributerApiView _wrapDaoToApiView(Distributer distributer) {
		float rate = distributerIncomeDao.get_beat_rate(IncomeType.EXP,
				distributer.getDistributerIncome(IncomeType.EXP).getIncome());
		DistributerApiView view = new DistributerApiView(distributer, rate);
		view.setHeadIconUrl(_getHeadIconUrl(distributer.getHeadIconName()));
		return view;
	}

	@Deprecated
	@Override
	public YiwuJson<DistributerApiView> modifyHeadIcon(int id, MultipartFile multipartFile, String fileSavePath) {
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
			float rate = distributerIncomeDao.get_beat_rate(IncomeType.EXP,
					distributer.getDistributerIncome(IncomeType.EXP).getIncome());
			return new YiwuJson<>(new DistributerApiView(distributer, rate));
	}

	@Override
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId) {
		try {
			CapitalAccount defaultAccount = distributerDao.get(distributerId).getDefaultCapitalAccount();
			if (defaultAccount == null) {
				return new YiwuJson<>("该用户尚未设置默认提现账户");
			}
			return new YiwuJson<>(new CapitalAccountApiView(defaultAccount));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@Override
	public YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId, String typeName) {
		YiwuJson<CapitalAccountApiView> yiwuJson = new YiwuJson<>();
		Distributer d = distributerDao.get(distributerId);
		Set<CapitalAccount> accounts = d.getCapitalAccounts();
		for (CapitalAccount a : accounts) {
			if (typeName.equals(a.getCapitalAccountType().getName())) {
				yiwuJson.setData(new CapitalAccountApiView(a));
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
	public YiwuJson<Boolean> judgePhoneNoIsRegistered(String phoneNo) {
		if (distributerDao.findCountByPhoneNo(phoneNo) > 0) {
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
		List<DistributerIncome> distributerIncomes = distributerIncomeDao.getTopN(IncomeType.BROKERAGE.getId(), 3);
		logger.debug(distributerIncomes == null ? 0 : distributerIncomes.size());
		List<TopThreeApiView> views = new ArrayList<>();
		for (DistributerIncome income : distributerIncomes) {
			TopThreeApiView v = new TopThreeApiView();
			try {
				v.setDistributerName(income.getDistributer().getName());
				v.setRegisterDate(income.getDistributer().getRegistedTime());
				v.setSumBrokerageIncome(income.getAccumulativeIncome());
				v.setSumShareTweetTimes(shareTweeetEventDao.findShareTweetTimes(income.getDistributer().getId()));
				v.setSumMemberCount(
						distributerDao.findCountByProperty(
								"superDistributer.id", 
								income.getDistributer().getId())
						.intValue());
				v.setSumOrderCount(
						orderDao.findCountByProperty(
								"customer.id", 
								income.getDistributer().getCustomer().getId())
						.intValue());
				v.setHeadIconUrl(_getHeadIconUrl(income.getDistributer().getHeadIconName()));
			} catch (Exception e) {
				logger.error(e);
			}
			views.add(v);
		}
		return views;
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
				logger.error(e);
				return new YiwuJson<>("服务器内部原因，头像保存失败");
			} catch (IOException e) {
				logger.error(e.getMessage());
				return new YiwuJson<>("图片保存目录 " + headIconSavePath + " 不存在");
			}
		}

		try {
			super.modify(distributerId, distributer);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e);
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

	private String _getHeadIconUrl(String headIconName) {
		if (StringUtils.hasLength(headIconName))
			return headIconUrl + headIconName;
		else
			return "";
	}
	
	

	@Override
	public EmpDistributerDto employeeLoginByWechat(String wechatNo) throws YiwuException {
		try {
			Distributer distributer = distributerDao.findByWechat(wechatNo);
			if(distributer.getEmployee() == null )
				throw new YiwuException("非内部员工不能登录系统。");
			return new EmpDistributerDto(distributer);
		} catch (DataNotFoundException e) {
			throw new YiwuException("用户不存在");
		}
	}


	@Override
	public PageBean<CustomerDto> findVisableDistributersByEmployee(int distributerId, String key,  int pageNo,
			int pageSize) throws YiwuException {
		//必须,找出自己的employeeId
		Distributer distributer = distributerDao.get(distributerId);
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

}