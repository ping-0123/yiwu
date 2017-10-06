package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.CapitalAccountType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.CourseApiView;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.model.view.TopThreeApiView;
import com.yinzhiwu.yiwu.service.CapitalAccountService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.service.impl.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/distributer")
@Api(value = "distributer")
public class DistributerApiController extends BaseController {

	@Autowired
	private DistributerService distributerService;
	@Autowired
	private CapitalAccountService capitalAccountService;
	@Autowired
	private FileService fileService;
	@Autowired private OrderYzwService orderService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("birthDay");
	}

	@RequestMapping(value="/register.do", method={RequestMethod.POST})
	@ApiOperation("注册新用户")
	public YiwuJson<DistributerRegisterModel> register(@Valid DistributerRegisterModel m, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new YiwuJson<>(getErrorsMessage(bindingResult));
		} 
		try {
			return distributerService.register(m);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getStackTrace());
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/loginByWechat", method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value ="使用微信openId登录")
	public YiwuJson<?> loginByWechat(@RequestParam String wechatNo, HttpSession session) {
		Distributer distributer = distributerService.findByWechatNo(wechatNo);
		if(distributer == null)
			return YiwuJson.createByErrorMessage("您尚未注册");
		session.setAttribute(Constants.CURRENT_USER, distributer);
		
		DistributerApiView view = new DistributerApiView(distributer);
		view.setHeadIconUrl(fileService.getFileUrl(distributer.getHeadIconName()));
		view.setBeatRate(distributerService.getExpWinRate(distributer));
		session.setAttribute(Constants.CURRENT_DISTRIBUTER_VIWE, view);
		
		 return YiwuJson.createBySuccess(view);
	}

	@PostMapping(value = "/loginByAccount")
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password) {
		return distributerService.loginByAccount(account, password);
	}

	/**
	 * @deprecated use {@link DistributerApiController#doGet(int)}
	 * @param id
	 * @return
	 */
	@Deprecated
	@GetMapping(value = "/getById/{id}")
	@ApiOperation("使用/api/distributer/{id}")
	public YiwuJson<DistributerApiView> getDistributerInfo(@PathVariable int id) {
		return distributerService.findById(id);
	}

	@GetMapping(value="/getInfo.do")
	@ApiOperation("获取个人信息")
	public YiwuJson<?> getInfo(@ApiParam(required=false) HttpSession session){
		DistributerApiView view =  (DistributerApiView) session.getAttribute(Constants.CURRENT_DISTRIBUTER_VIWE);
		if(view == null )
			return YiwuJson.createByErrorMessage("请登录");
		return YiwuJson.createBySuccess(view);
	}
	
	@GetMapping(value = "/{id}")
	public YiwuJson<DistributerApiView> doGet(@PathVariable int id, HttpSession session) {
		return distributerService.findById(id);
	}

	@GetMapping(value="/{id}/courses")
	@ApiOperation(value="获取客户的课程")
	public YiwuJson<?> getCoures(@PathVariable Integer id, 
			@ApiParam(name="课程类型",defaultValue="CLOSED",required=false) 
			@RequestParam(name="courseType", required=false, defaultValue="CLOSED") CourseType courseType)
	{
		try {
			Distributer distributer = distributerService.get(id);
			if(distributer.getCustomer()==null){
				logger.error("distributer " + id + "未绑定客户");
				throw new Exception("distributer " + id + "未绑定客户");
			}
			
			List<CourseYzw> courses = orderService.findCoursesByCustomerIdAndCourseType(distributer.getCustomer().getId(),courseType);
			List<CourseApiView> views = new ArrayList<>();
			for (CourseYzw courseYzw : courses) {
				views.add(CourseApiView.fromDAO(courseYzw));
			}
			
			return YiwuJson.createBySuccess(views);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YiwuJson.createByErrorMessage(e.getMessage());
		}
	}
	
	@GetMapping(value = "/capitalAccount/getDefault")
	@ApiOperation(value = "获取默认的提现帐号")
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(Integer distributerId) {
		return distributerService.getDefaultCapitalAccount(distributerId);
	}

	@PostMapping(value = "/capitalAccount/setDefault")
	@ApiOperation(value = "设置默认提现帐号")
	public YiwuJson<Boolean> setDefaultCapitalAccount(@ApiParam(value = "分销者Id", required = true) int distributerId,
			@ApiParam(value = "帐号Id", required = true) int accountId) {
		try {
			distributerService.setDefaultCapitalAccount(distributerId, accountId);
			return new YiwuJson<>(new Boolean(true));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@GetMapping(value = "/capitalAccount")
	@ApiOperation(value = "获取资金帐号")
	public YiwuJson<List<CapitalAccountApiView>> getCapitalAccount(int distributerId,
			@ApiParam(value = "帐号类型Id, 10001：微信支付,10002:支付宝支付, -1：全部支付类型") int accountTypeId) {
		List<CapitalAccountApiView> views = new ArrayList<>();
		List<CapitalAccount> accounts = new ArrayList<>();
		if (accountTypeId == -1) {
			accounts = capitalAccountService.findByDistributerId( distributerId);
		} else {
			accounts = capitalAccountService.findByTypeAndDistributerId(accountTypeId, distributerId);
		}
		for (CapitalAccount capitalAccount : accounts) {
			views.add(new CapitalAccountApiView(capitalAccount));
		}

		return new YiwuJson<>(views);
	}

	@PostMapping(value = "/capitalAccount")
	@ApiOperation(value = "新增资金账户")
	public YiwuJson<CapitalAccountApiView> addCapitalAccount(
			@ApiParam("distributerId accountTypeId accountName 必须") @Valid CapitalAccountApiView capitalAcountModel,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new YiwuJson<>(getErrorsMessage(bindingResult));
		}
		try {
			CapitalAccount capitalAccount = new CapitalAccount();
			capitalAccount.setAccount(capitalAcountModel.getAccountName());
			Distributer distributer = new Distributer();
			distributer.setId(capitalAcountModel.getDistributerId());
			CapitalAccountType type = new CapitalAccountType();
			type.setId(capitalAcountModel.getAccountTypeId());
			capitalAccount.setDistributer(distributer);
			capitalAccount.setCapitalAccountType(type);
			logger.debug("begin save the new capitalAccount");
			capitalAccountService.save(capitalAccount);
			logger.debug("save the new capitalAccount successfully");
			return new YiwuJson<>(new CapitalAccountApiView(capitalAccount));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	/**
	 * @deprecated not supported
	 * @param model
	 * @return
	 */
	@Deprecated
	@GetMapping(value = "/input")
	public ModelAndView inputProduct(Model model) {
		model.addAttribute("distributerApiView", new DistributerApiView());
		return new ModelAndView("distributer/form");
	}

	@GetMapping(value = "validatyPhoneNo")
	public YiwuJson<Boolean> validatyIsRegister(String phoneNo) {
		if (!phoneNo.matches("^1\\d{10}$"))
			return new YiwuJson<>("请输入合法的11位数手机号码");
		return distributerService.judgePhoneNoIsRegistered(phoneNo);
	}

	@GetMapping(value = "/editform")
	public ModelAndView getModifyForm(Model model) {
		DistributerModifyModel distributer = new DistributerModifyModel();
		model.addAttribute("model", distributer);
		return new ModelAndView("distributer/form");
	}

	@RequestMapping(value = "/{distributerId}", method = { RequestMethod.PUT, RequestMethod.POST })
	@ApiOperation("修改会员个人资料")
	public YiwuJson<DistributerModifyModel> modify(DistributerModifyModel model, @PathVariable int distributerId) {
		if (model == null)
			return YiwuJson.createByErrorMessage("没有需要修改的项");
		if (model.getImage() != null && model.getImage().getSize() > 500 * 1024)
			return YiwuJson.createByErrorMessage("您上传的头像太大， 请保存在500Kb以下");

		return distributerService.modify(distributerId, model);

	}

	@GetMapping("/getTopThree")
	@ApiOperation(value = "获取收入前三名的分销者")
	public YiwuJson<List<TopThreeApiView>> getTopThree() {
		return new YiwuJson<>(distributerService.getBrokerageTopThree());
	}
	
	@GetMapping("/{distributerId}/defaultStore")
	@ApiOperation(value="返回客户默认的上课门店")
	public YiwuJson<StoreApiView> getDefaultStore(
			@PathVariable(name="distributerId", required=true) Integer distributerId)
	{
		return  distributerService.findDefaultStoreApiView(distributerId);
	}
}
