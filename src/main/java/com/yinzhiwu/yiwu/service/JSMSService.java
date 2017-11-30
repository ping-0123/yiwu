package com.yinzhiwu.yiwu.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.yinzhiwu.yiwu.context.JpushConfig;
import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;
import com.yinzhiwu.yiwu.event.PayWithdrawEvent;
import com.yinzhiwu.yiwu.event.WithdrawEvent;
import com.yinzhiwu.yiwu.exception.JSMSException;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.JSMSClient;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.model.SMSPayload;
import cn.jsms.api.common.model.SMSPayload.Builder;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月6日 下午9:15:06
*
*/

@Service
public class JSMSService {
	private static Logger LOG = LoggerFactory.getLogger(JSMSService.class);
	
	private JSMSClient jsmsClient;
	private Map<JSMSTemplate,Map<String,String> > msgIdMap = new HashMap<>();
	private DateFormat dateFormat;
	private NumberFormat numberFormat;
	
	@Autowired
	public JSMSService(JpushConfig config){
		jsmsClient = new JSMSClient(config.getMasterSecret(),config.getAppKey());
		for(JSMSTemplate template: JSMSTemplate.values())
		{
			if(JSMSTemplateType.VALIDATE==template.getType())
				msgIdMap.put(template,new HashMap<>());
		}
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		numberFormat = NumberFormat.getCurrencyInstance();
	}
	
	public boolean sendRegisterSMSCode(String mobieNumber) throws JSMSException{
		return sendSMSCode(mobieNumber,JSMSTemplate.REGISTER);
	}
	
	public boolean sendWithDrawSMSCode(String mobieNumber) throws JSMSException{
		return sendSMSCode(mobieNumber, JSMSTemplate.WITHDRAW);
	}
	
	public boolean sendPaydepositSMSCode(String mobieNumber) throws JSMSException{
		return sendSMSCode(mobieNumber, JSMSTemplate.PAYDEPOSIT);
	}
	
	public boolean validateRegisterSMSCode(String mobileNumber, String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.REGISTER, mobileNumber, code);
	}
	
	public boolean validateWithDrawSMSCode(String mobileNumber, String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.WITHDRAW, mobileNumber, code);
	}
	
	public boolean validatePaydepositSMSCode(String mobileNumber, String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.PAYDEPOSIT, mobileNumber, code);
	}
	
	public boolean sendSetDefaultCapitalAccountSMSCode(String mobileNumber) throws JSMSException{
		return sendSMSCode(mobileNumber, JSMSTemplate.SET_DEFAULT_CAPITAL_ACCOUNT);
	}
	
	public boolean validateSetDefaultCapitalAccountSMSCode(String mobileNumber, String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.SET_DEFAULT_CAPITAL_ACCOUNT, mobileNumber, code);
	}
	
	
	public boolean sendAddNewCapitalAccountSMSCode(String mobileNumber) throws JSMSException{
		return sendSMSCode(mobileNumber, JSMSTemplate.ADD_NEW_CAPITAL_ACCOUNT);
	}
	
	public boolean validateAddNewCapitalAccountSMSCode(String moibileNumber,String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.ADD_NEW_CAPITAL_ACCOUNT, moibileNumber, code);
	}
	
	public boolean sendUnbindMobileNumberSMSCode(String mobileNumber) throws JSMSException{
		return sendSMSCode(mobileNumber, JSMSTemplate.UNBIND_MOBILE_NUMBER);
	}
	
	public boolean validateUnbindMobileNumberSMSCode(String moibileNumber,String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.UNBIND_MOBILE_NUMBER, moibileNumber, code);
	}
	
	public boolean sendBindMobileNumberSMSCode(String mobileNumber) throws JSMSException{
		return sendSMSCode(mobileNumber, JSMSTemplate.BIND_MOBILE_NUMBER);
	}
	
	public boolean validateBindMobileNumberSMSCode(String moibileNumber,String code) throws JSMSException{
		return validateSMSCode(JSMSTemplate.BIND_MOBILE_NUMBER, moibileNumber, code);
	}
	
	public boolean sendPayWithdrawMessage(String mobileNumber, java.util.Date date, Float amount) throws JSMSException{
		String[] params = new String[]{dateFormat.format(date), numberFormat.format(amount)};
		return sendTemplateMessage(mobileNumber, JSMSTemplate.PAY_WITHDRAW, params);
	}
	
	public boolean sendDoWithdrawMessage(String mobileNumber, Date date, Float amount) throws JSMSException
	{
		String[] params = new String[]{dateFormat.format(date), numberFormat.format(amount)};
		return sendTemplateMessage(mobileNumber, JSMSTemplate.DO_WITHDRAW, params);
	}
	
	private boolean sendTemplateMessage(String mobileNumber, JSMSTemplate template, String[] params) throws JSMSException{
		if(template.getParams().length != params.length)
			throw new IllegalArgumentException("模板参数数量与传入的参数数量不一致");
		
		Builder builder = SMSPayload.newBuilder()
				.setMobileNumber(mobileNumber)
				.setTempId(template.getId());
		for(int i=0; i< template.getParams().length; i++){
			builder.addTempPara(template.getParams()[i], params[i]);
		}
		
		SMSPayload payload = builder.build();
		
		try {
			jsmsClient.sendTemplateSMS(payload);
			return true;
		} catch (APIConnectionException | APIRequestException e) {
			handleException(e);
		}
		
		return true;
		
	}
	
	public boolean sendSMSCode(String mobieNumber, JSMSTemplate template) throws JSMSException{
		SMSPayload payload = SMSPayload.newBuilder()
				.setMobileNumber(mobieNumber)
				.setTempId(template.getId())
				.build();
		
		try {
			SendSMSResult sendSMSCode = jsmsClient.sendSMSCode(payload);
			msgIdMap.get(template).put(mobieNumber, sendSMSCode.getMessageId());
			return true;
		} catch (APIConnectionException | APIRequestException e) {
			handleException(e);
		}
		return true;
		
	}
	
	public boolean validateSMSCode(JSMSTemplate template,String mobileNumber, String code) throws JSMSException{
		String msgId = msgIdMap.get(template).get(mobileNumber);
		if(null==msgId){
			String message = mobileNumber + "未验证, 请先请求验证码";
			LOG.error(message);
			throw new JSMSException(message);
		}
		
		try {
			ValidSMSResult validSMSResult = jsmsClient.sendValidSMSCode(msgId, code);
			if(validSMSResult.getIsValid()){
				msgIdMap.get(template).remove(mobileNumber);
				return true;
			}
		} catch (APIConnectionException | APIRequestException e) {
			handleException(e);
		}
		
		return true;
	}
	
	private void handleException(Exception e) throws JSMSException{
		if(e instanceof APIConnectionException){
			LOG.error("Connection error. Should retry later. ", e);
			LOG.info(e.getMessage());
			throw new JSMSException(e.getMessage(),e);
		}else if (e instanceof APIRequestException) {
			APIRequestException ae = (APIRequestException) e;
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + ((APIRequestException) e).getStatus());
            LOG.info("Error Message: " + e.getMessage() + ((APIRequestException)e).getErrorMessage());
            throw new JSMSException(ae.getErrorCode() + " " + ae.getErrorMessage(), ae);
		}else {
			LOG.error("other exception. ",e);
			throw new JSMSException(e.getMessage(),e);
		}
	}
	
	public enum JSMSTemplate{
		REGISTER(47091, JSMSTemplateType.VALIDATE, new String[]{"code"}),
		LOGIN(146213, JSMSTemplateType.VALIDATE, new String[]{"code"}),
		WITHDRAW(82007, JSMSTemplateType.VALIDATE, new String[]{"code"}),
		PAYDEPOSIT(102276, JSMSTemplateType.VALIDATE,new String[]{"code"}),
		SET_DEFAULT_CAPITAL_ACCOUNT(145906,JSMSTemplateType.VALIDATE, new String[]{"code"}),
		ADD_NEW_CAPITAL_ACCOUNT(145907,JSMSTemplateType.VALIDATE, new String[]{"code"}),
		UNBIND_MOBILE_NUMBER(146193,JSMSTemplateType.VALIDATE, new String[]{"code"}),
		BIND_MOBILE_NUMBER(146194,JSMSTemplateType.VALIDATE, new String[]{"code"}),
		GENERAL(146445,JSMSTemplateType.VALIDATE, new String[]{"code"}),
		PAY_WITHDRAW(145602,JSMSTemplateType.NOTIFICATION, new String[]{"date","amount"} ),
		DO_WITHDRAW(145601, JSMSTemplateType.NOTIFICATION, new String[]{"date", "amount"});
		
		private final int id;
		private final JSMSTemplateType type;
		private final String[] params;

		private JSMSTemplate(int id, JSMSTemplateType type, String[] params) {
			this.id = id;
			this.type = type;
			this.params=params;
		}

		public int getId() {
			return id;
		}

		public JSMSTemplateType getType() {
			return type;
		}

		public String[] getParams() {
			return params;
		}

		
	}
	
	public enum JSMSTemplateType{
		VALIDATE,
		NOTIFICATION,
		MARKETING
	}
	
	@Async
	@TransactionalEventListener(classes={PayWithdrawEvent.class})
	public void handlePayWithdrawEvent(PayWithdrawEvent event){
		WithdrawBrokerage withdraw = (WithdrawBrokerage) event.getSource();
		try {
			sendPayWithdrawMessage(withdraw.getDistributer().getPhoneNo(), withdraw.getCreateTime(), withdraw.getAmount());
		} catch (JSMSException e) {
			LOG.error("发送提现消息失败", e);
		}
	}
	
	@Async
	@TransactionalEventListener(classes={WithdrawEvent.class})
	public void handleWithdrawEvent(WithdrawEvent event){
		WithdrawBrokerage withdraw = (WithdrawBrokerage) event.getSource();
		try {
			sendDoWithdrawMessage(withdraw.getDistributer().getPhoneNo(), withdraw.getCreateTime(),withdraw.getAmount());
		} catch (JSMSException e) {
			LOG.error("发送提现消息失败", e);
		}
	}
}
