package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.exception.JSMSException;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView.CapitalAccountApiViewConverter;
import com.yinzhiwu.yiwu.service.CapitalAccountService;
import com.yinzhiwu.yiwu.service.JSMSService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月13日 下午11:26:31
*
*/

@RestController
@RequestMapping("/api/capitalAccounts")
@Api(description="提现账号APIs")
public class CapitalAccountApiController extends BaseController {

	@Autowired private CapitalAccountService capitalAccountService;
	@Autowired private JSMSService jsmsService;
	
	@GetMapping
	@ApiOperation(value="获取资金账号. 1.获取默认账号(?isDefault=true), 2.获取指定类型账号如微信账号(?paymentMode=WECHAT_PAY)")
	public YiwuJson<List<CapitalAccountApiView>> doGet(
			@ApiParam(value="是否获取默认账号, 不传值则取全部", required=false)
			Boolean isDefault,
			@ApiParam(value = "支付类型, 默认全部", required=false, allowableValues="{WECHAT_PAY,ALI_PAY}")
			PaymentMode paymentMode)
	{
		Distributer distributer = UserContext.getDistributer();
		CapitalAccount example = new CapitalAccount();
		example.setDistributer(distributer);
		example.setIsDefault(isDefault);
		example.setPaymentMode(paymentMode);
		
		List<CapitalAccount> accounts = capitalAccountService.findByExample(example);
		List<CapitalAccountApiView> views = new ArrayList<>();
		for(CapitalAccount account: accounts){
			views.add(CapitalAccountApiViewConverter.INSTANCE.fromPO(account));
		}
		
		return YiwuJson.createBySuccess(views);
	}
	
	@PostMapping(value="/sendAddNewCapitalAccountSmsCode")
	@ApiOperation(value="发送新增提现账号的短信验证码")
	public YiwuJson<?> sendAddNewCapitalAccountSmsCode() throws JSMSException{
		return YiwuJson.createBySuccess(
				jsmsService.sendAddNewCapitalAccountSMSCode(UserContext.getDistributer().getPhoneNo()));
	}
	
	@PostMapping(value="/sendSetDefaultCapitalAccountSmsCode")
	@ApiOperation(value="发送设置默认账号的短信验证码")
	public YiwuJson<?> sendSetDefaultCapitalAccountSmsCode() throws JSMSException{
		return YiwuJson.createBySuccess(
				jsmsService.sendSetDefaultCapitalAccountSMSCode(UserContext.getDistributer().getPhoneNo()));
	}
	
	@PostMapping
	@ApiOperation(value="新增提现账号")
	public YiwuJson<CapitalAccountApiView> doCreate(CapitalAccountApiView account, String code) throws JSMSException{
		Distributer distributer = UserContext.getDistributer();
		if(jsmsService.validateAddNewCapitalAccountSMSCode(distributer.getPhoneNo(), code)){
			CapitalAccount account2 = CapitalAccountApiViewConverter.INSTANCE.toPO(account);
			account2.setDistributer(distributer);
			capitalAccountService.save(account2);
			return YiwuJson.createBySuccess(
					CapitalAccountApiViewConverter.INSTANCE.fromPO(account2));
		}else{
			return YiwuJson.createByErrorMessage("短信吗验证失败");
		}
	
	}
	
	@PutMapping("/{id}/isDefault")
	@ApiOperation("设置默认提现账号  /api/capitalAccounts/{id}/isDefault")
	public YiwuJson<CapitalAccountApiView> setDefaultCapitalAccount(
			@PathVariable(name="id") Integer id,
			@RequestParam(name="isDefault", required=false, defaultValue="true")
			@ApiParam(name="isDefault", required=false, defaultValue="true")
			Boolean isDefault,
			String code) throws DataNotFoundException, JSMSException
	{
		Distributer distributer = UserContext.getDistributer();
		if(jsmsService.validateSetDefaultCapitalAccountSMSCode(distributer.getPhoneNo(), code)){
			CapitalAccount account = capitalAccountService.get(id);
			account.setIsDefault(isDefault);
			capitalAccountService.update(account);
			return YiwuJson.createBySuccess(CapitalAccountApiViewConverter.INSTANCE.fromPO(account));
		}else
			return YiwuJson.createByErrorMessage("短信码验证失败");
	}
}
