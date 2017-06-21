package com.yinzhiwu.springmvc3.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.CapitalAccountType;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.CapitalAccountModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerApiView;
import com.yinzhiwu.springmvc3.model.view.TopThreeApiView;
import com.yinzhiwu.springmvc3.service.CapitalAccountService;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.util.UrlUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/distributer")
public class DistributerController extends BaseController {
	
	@Autowired private DistributerService  distributerService;
	@Autowired private CapitalAccountService capitalAccountService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		dataBinder.setDisallowedFields("birthDay");
	}


	@PostMapping(value="")
	public YiwuJson<DistributerApiView> register(@Valid Distributer m, String invitationCode, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return new YiwuJson<>(getErrorsMessage(bindingResult));
		}
		try{
			return distributerService.register(invitationCode,m);
		}catch (Exception e) {
			e.printStackTrace();
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@PostMapping(value="/loginByWechat")
	public YiwuJson<DistributerApiView> loginByWechat(@RequestParam String  wechatNo ){
		return distributerService.loginByWechat(wechatNo);
	}
	
	
	@GetMapping(value="/loginByAccount")
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password){
		return distributerService.loginByAccount(account,password);
	}
	
	@Deprecated
	@GetMapping(value="/getById/{id}")
	public YiwuJson<DistributerApiView> getDistributerInfo(@PathVariable int id){
		return distributerService.findById(id);
	}
	
	
	@GetMapping(value="/{id}")
	public YiwuJson<DistributerApiView> doGet(@PathVariable int id){
		return distributerService.findById(id);
	}
	
	
	@PostMapping(value="/modifyHeadIcon")
	public YiwuJson<DistributerApiView>  modifyHeadIcon(HttpServletRequest servletRequest,
				@Valid DistributerApiView d, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return new YiwuJson<>(fieldError.getField() + " " + fieldError.getDefaultMessage());
		}
		
		String parentPath = servletRequest.getServletContext().getRealPath(UrlUtil.HEAD_ICON_PATH);
		return distributerService.modifyHeadIcon(
				d.getId(),
				d.getImage(), 
				parentPath);
	}
	
	@GetMapping(value="/capitalAccount/getDefault")
	@ApiOperation(value="获取默认的提现帐号")
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId){
		try {
			logger.info("capitalAccount/getDefault start with parameter" + distributerId);
			Distributer distributer = distributerService.get(3000050);
			logger.info("获取到Distributer" + distributer);
			if(distributer == null) throw new Exception("Id为" + distributerId + "的客户不存在");
			CapitalAccount capitalAccount = distributer.getDefaultCapitalAccount();
			if(capitalAccount == null) throw new Exception("该用户尚未设置默认提现帐号");
			return new YiwuJson<>(new CapitalAccountApiView(capitalAccount));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@PostMapping(value="/capitalAccount/setDefault")
	@ApiOperation(value="设置默认提现帐号")
	public YiwuJson<Boolean> setDefaultCapitalAccount(
			@ApiParam(value="分销者Id", required=true) int distributerId,
			@ApiParam(value="帐号Id", required=true) int accountId)
	{
		try{
			logger.debug("start");
			Distributer distributer = distributerService.get(distributerId);
			if(distributer == null) throw new Exception("系统中不存在distributerId为:" + distributerId + "的分销者用户");
			CapitalAccount account = capitalAccountService.get(accountId);
			if(account == null) throw new Exception("请输入正确的accountId");
			if(!account.getDistributer().equals(distributer)) throw  new Exception("帐号" + accountId + "不属于" + distributerId + ",不能设置为其默认提现帐号");
			distributer.setDefaultCapitalAccount(account);
			distributerService.update(distributer);
			return new YiwuJson<>(new Boolean(true));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping(value="/capitalAccount")
	@ApiOperation(value="获取资金帐号")
	public YiwuJson<List<CapitalAccountApiView>> getCapitalAccount(
			int distributerId,
			@ApiParam(value="帐号类型Id, 10001：微信支付,10002:支付宝支付, -1：全部支付类型") int accountTypeId)
	{
		List<CapitalAccountApiView> views = new ArrayList<>();
		List<CapitalAccount> accounts = new ArrayList<>();
		if(accountTypeId == -1){
			accounts = capitalAccountService.findByProperty("distributer.id", distributerId);
		}else{
			try {
				accounts = capitalAccountService.findByProperties(
						new String[]{"distributer.id", "capitalAccountType.id" },  
						new Object[]{distributerId, accountTypeId});
			} catch (DataNotFoundException e) {
				accounts = new ArrayList<>();
			}
		}
		for (CapitalAccount capitalAccount : accounts) {
			views.add(new CapitalAccountApiView(capitalAccount));
		}
		
		return new YiwuJson<>(views);
	}
	
	@PostMapping(value="/capitalAccount")
	@ApiOperation(value="新增资金账户")
	public YiwuJson<CapitalAccountApiView> addCapitalAccount(
			@Valid CapitalAccountModel capitalAcountModel, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return new YiwuJson<>(getErrorsMessage(bindingResult));
		}
		try{
			CapitalAccount capitalAccount = new CapitalAccount();
			capitalAccount.setAccount(capitalAcountModel.getAccountName());
			Distributer distributer = new Distributer();
			distributer.setId(capitalAcountModel.getDistributerId());
			CapitalAccountType type = new CapitalAccountType();
			type.setId(capitalAcountModel.getCapitalAccountTypeId());
			capitalAccount.setDistributer(distributer);
			capitalAccount.setCapitalAccountType(type);
			logger.debug("begin save the new capitalAccount");
			capitalAccountService.save(capitalAccount);
			logger.debug("save the new capitalAccount successfully");
			return new YiwuJson<>(new CapitalAccountApiView(capitalAccount));
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
   @RequestMapping(value = "/input")
    public ModelAndView inputProduct(Model model) {
        model.addAttribute("distributerApiView", new DistributerApiView());
        return new ModelAndView("distributer/form");
    }

   
   @GetMapping(value="validatyPhoneNo")
   public YiwuJson<Boolean> validatyIsRegister(String phoneNo){
	   if(! phoneNo.matches("^1\\d{10}$"))
		   return new YiwuJson<>("请输入合法的11位数手机号码");
	   return distributerService.judgePhoneNoIsRegistered(phoneNo);
   }
	  

   
   
   @RequestMapping(value="/{id}", method={RequestMethod.PUT, RequestMethod.POST})
   public YiwuJson<Boolean> modify(Distributer d, @PathVariable int id){
		try {
			distributerService.modify(id, d);
			return new YiwuJson<>(new Boolean(true));
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage());
			return new YiwuJson<>(e.getMessage());
		}
	   
   }
   
   @GetMapping("/getTopThree")
   @ApiOperation(value="获取收入前三名的分销者")
   public YiwuJson<List<TopThreeApiView>> getTopThree(){
	   return new YiwuJson<>(distributerService.getBrokerageTopThree());
   }
}
