package com.yinzhiwu.yiwu.controller.api;


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

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.type.CapitalAccountType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;
import com.yinzhiwu.yiwu.model.view.TopThreeApiView;
import com.yinzhiwu.yiwu.service.CapitalAccountService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.util.UrlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/distributer")
@Api(value="distributer")
public class DistributerApiController extends BaseController {
	
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
		return distributerService.getDefaultCapitalAccount(distributerId);
	}
	
	@PostMapping(value="/capitalAccount/setDefault")
	@ApiOperation(value="设置默认提现帐号")
	public YiwuJson<Boolean> setDefaultCapitalAccount(
			@ApiParam(value="分销者Id", required=true) int distributerId,
			@ApiParam(value="帐号Id", required=true) int accountId)
	{
		try{
			distributerService.setDefaultCapitalAccount(distributerId, accountId);
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
			@ApiParam("distributerId accountTypeId accountName 必须") @Valid CapitalAccountApiView capitalAcountModel,
			BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return new YiwuJson<>(getErrorsMessage(bindingResult));
		}
		try{
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
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@Deprecated
   @GetMapping(value = "/input")
    public ModelAndView inputProduct(Model model) {
        model.addAttribute("distributerApiView", new DistributerApiView());
        return new	 ModelAndView("distributer/form");
    }

   
   @GetMapping(value="validatyPhoneNo")
   public YiwuJson<Boolean> validatyIsRegister(String phoneNo){
	   if(! phoneNo.matches("^1\\d{10}$"))
		   return new YiwuJson<>("请输入合法的11位数手机号码");
	   return distributerService.judgePhoneNoIsRegistered(phoneNo);
   }
	  

   
   
   @RequestMapping(value="/{distributerId}", method={RequestMethod.PUT, RequestMethod.POST})
   @ApiOperation("修改会员个人资料")
   public YiwuJson<DistributerModifyModel> modify(DistributerModifyModel model, @PathVariable int distributerId){
		try {
			Distributer d = new Distributer();
			d.setNickName(model.getNickName());
			d.setName(model.getName());
			d.setPhoneNo(model.getPhoneNo());
			distributerService.modify(distributerId, d);
			return new YiwuJson<>(model);
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
