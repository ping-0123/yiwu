package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.enums.CourseType;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.CourseVO;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;
import com.yinzhiwu.yiwu.model.view.DistributerApiView.DistributerApiViewConverter;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView.CapitalAccountApiViewConverter;
import com.yinzhiwu.yiwu.service.CapitalAccountService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/distributer")
@Api(value = "distributer")
public class DistributerApiController extends BaseController {

	@Autowired private DistributerService distributerService;
	@Autowired private CapitalAccountService capitalAccountService;
	@Qualifier("fileServiceImpl")
	@Autowired private FileService fileService;
	@Autowired private OrderYzwService orderService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("birthDay");
	}



	@GetMapping(value="/information")
	@ApiOperation("获取个人信息")
	public YiwuJson<?> getInfo(){
		Distributer distributer = UserContext.getDistributer();
		DistributerApiViewConverter converter = DistributerApiView.DistributerApiViewConverter.instance;
		return YiwuJson.createBySuccess(converter.fromPO(distributer));
	}
	
	@GetMapping(value = "/{id}")
	public YiwuJson<DistributerApiView> doGet(@PathVariable int id, HttpSession session) throws DataNotFoundException {
		Distributer distributer =  distributerService.get(id);
		return YiwuJson.createBySuccess( DistributerApiViewConverter.instance.fromPO(distributer));
	}

	@GetMapping(value="/myClosedCourses")
	@ApiOperation(value="获取客户的私教课课程")
	public YiwuJson<List<CourseVO>> findClosedCourses(){
		Distributer distributer = UserContext.getDistributer();
		List<CourseYzw> courses = orderService.findCoursesByCustomerIdAndCourseType(distributer.getCustomer().getId(),CourseType.CLOSED);
		List<CourseVO> views = new ArrayList<>();
		for (CourseYzw courseYzw : courses) {
			views.add(new CourseVO().fromPO(courseYzw));
		}
		
		return YiwuJson.createBySuccess(views);
	}
	
	/**
	 * {@linkplain /api/distributer/myClosedCourses}
	 * @param id
	 * @param courseType
	 * @return
	 */
	@Deprecated
	@GetMapping(value="/{id}/courses")
	@ApiOperation(value="获取客户的课程")
	public YiwuJson<List<CourseVO>> getCoures(@PathVariable Integer id, 
			@ApiParam(name="courseType",defaultValue="CLOSED",required=false,value="CLOSED,OPENED,PRIVATE") 
			@RequestParam(name="courseType", required=false, defaultValue="CLOSED") CourseType courseType)
	{
		try {
			Distributer distributer = distributerService.get(id);
			if(distributer.getCustomer()==null){
				logger.error("distributer " + id + "未绑定客户");
				throw new Exception("distributer " + id + "未绑定客户");
			}
			
			List<CourseYzw> courses = orderService.findCoursesByCustomerIdAndCourseType(distributer.getCustomer().getId(),courseType);
			List<CourseVO> views = new ArrayList<>();
			for (CourseYzw courseYzw : courses) {
				views.add(new CourseVO().fromPO(courseYzw));
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

	/**
	 * @deprecated {@link CapitalAccountApiController#doGet(Boolean, PaymentMode)}
	 * @param paymentMode
	 * @return
	 */
	@Deprecated
	@GetMapping(value = "/capitalAccounts")
	@ApiOperation(value = "获取资金帐号,已启用， 请使用 /api/capitalAccounts")
	public YiwuJson<List<CapitalAccountApiView>> getCapitalAccount(
			@ApiParam(value = "支付类型, 默认全部", required=false, allowableValues="{WECHAT_PAY,ALI_PAY}") 
			PaymentMode paymentMode) {
		Distributer distributer = UserContext.getDistributer();
		
		List<CapitalAccount> accounts = new ArrayList<>();
		if (null == paymentMode) {
			accounts = capitalAccountService.findByDistributerId(distributer.getId());
		} else {
			accounts = capitalAccountService.findByDistributerAndPaymentMode(distributer,paymentMode);
		}
		
		List<CapitalAccountApiView> vos = new ArrayList<>();
		for (CapitalAccount capitalAccount : accounts) {
			vos.add(CapitalAccountApiViewConverter.INSTANCE.fromPO(capitalAccount));
		}

		return new YiwuJson<>(vos);
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
//			capitalAccount.setAccount(capitalAcountModel.getAccountName());
			Distributer distributer = new Distributer();
			distributer.setId(capitalAcountModel.getDistributerId());
//			PaymentMode type = PaymentMode.fromId(capitalAcountModel.getAccountTypeId());
			capitalAccount.setDistributer(distributer);
//			capitalAccount.setPaymentMode(type);
			logger.debug("begin save the new capitalAccount");
			capitalAccountService.save(capitalAccount);
			logger.debug("save the new capitalAccount successfully");
//			return new YiwuJson<>(new CapitalAccountApiView(capitalAccount));
			return null;
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
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

	
	@GetMapping("/{distributerId}/defaultStore")
	@ApiOperation(value="返回客户默认的上课门店")
	public YiwuJson<StoreApiView> getDefaultStore(
			@PathVariable(name="distributerId", required=true) Integer distributerId)
	{
		return  distributerService.findDefaultStoreApiView(distributerId);
	}
}
