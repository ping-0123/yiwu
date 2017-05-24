package com.yinzhiwu.springmvc3.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinzhiwu.springmvc3.dao.impl.DistributerDaoImpl;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerRegisterApiView;
import com.yinzhiwu.springmvc3.service.CapitalAccountService;
import com.yinzhiwu.springmvc3.service.DistributerService;
import com.yinzhiwu.springmvc3.util.UrlUtil;

@RestController
@RequestMapping("/api/distributer")
public class DistributerController {
	private static final Log logger = LogFactory.getLog(DistributerDaoImpl.class);
	
	
	@Autowired
	private DistributerService  distributerService;
	
	@Autowired
	private CapitalAccountService caService;

	@RequestMapping(value="/register", method={RequestMethod.POST})
	public YiwuJson<DistributerApiView> register(String invitationCode,
										@Valid @ModelAttribute Distributer distributer,
										BindingResult bindingResult,
										Model model){
		if(bindingResult.hasErrors()){
			 FieldError field = bindingResult.getFieldError();
			 String message =   field.getField() + " " + field.getDefaultMessage();
			 logger.info(message);
			 return new YiwuJson<>(200,false,message,null,false);
		}
		
		return  distributerService.register(invitationCode, distributer);
	}
	
	
	@RequestMapping(value="/loginByWechat", method={RequestMethod.POST,RequestMethod.GET})
	public YiwuJson<DistributerApiView> loginByWechat(@RequestParam String  wechatNo ){
		return distributerService.loginByWechat(wechatNo);
	}
	
	
	@GetMapping(value="/loginByAccount")
	public YiwuJson<DistributerApiView> loginByAccount(String account, String password){
		return distributerService.loginByAccount(account,password);
	}
	
	@GetMapping(value="/getById/{id}")
	public YiwuJson<DistributerApiView> getDistributerInfo(@PathVariable int id){
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
	public YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId){
		return distributerService.getDefaultCapitalAccount(distributerId);
	}
	
	@PostMapping(value="/capitalAccount/setDefault")
	public YiwuJson<Boolean> setDefaultCapitalAccount(@Valid CapitalAccountApiView v, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				if("distributerId".equals(fieldError.getField()) 
						|| "accountId".equals(fieldError.getField())){
					return new YiwuJson<>(fieldError.getField() + " " + fieldError.getDefaultMessage());
				}
			}
		}
		
		distributerService.setDefaultCapitalAccount(v.getDistributerId(), v.getAccountId());
		return new YiwuJson<>(new Boolean(true));
	}
	
	@GetMapping(value="capitalAccount")
	public YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId,
			 CapitalAccountApiView m, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return new YiwuJson<>(fieldError.getField() + " " + fieldError.getDefaultMessage());
		}
		
		return distributerService.getCapitalAccount(distributerId,m.getTypeName());
	}
	
	@PostMapping(value="capitalAccount")
	public YiwuJson<CapitalAccountApiView> addCapitalAccount(
			@Valid CapitalAccountApiView v, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			logger.info("addCapitalAccount has erorr field" +  fieldErrors);
			for (FieldError fieldError : fieldErrors) {
				if(!fieldError.getField().equals("accountId"))
					return new YiwuJson<>(fieldError.getField() + " " + fieldError.getDefaultMessage());
			}
			
		}
		return caService.addCapitalAccount(v);
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
	  
   @GetMapping(value="/registerRecords/subordinates")
   public YiwuJson<List<DistributerRegisterApiView>> findSubordiatesRegisterRecords(int distributerId){
	    return distributerService.findSubordiatesRegisterRecords(distributerId);
   }
   
   
   @GetMapping(value="registerRecords/secondaries")
   public YiwuJson<List<DistributerRegisterApiView>> findSecondariesRegisterRecords(int distributerId){
	   return distributerService.findSecondariesRegisterRecords(distributerId);
   }
}
