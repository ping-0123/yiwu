package com.yinzhiwu.yiwu.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.context.JpushConfig;

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
	
	public boolean sendRegisterMessage(String mobieNumber){
		return sendValidateMessage(mobieNumber,JSMSTemplate.REGISTER);
	}
	
	public boolean sendWithDrawMessage(String mobieNumber){
		return sendValidateMessage(mobieNumber, JSMSTemplate.WITHDRAW);
	}
	
	public boolean sendPaydepositMessage(String mobieNumber){
		return sendValidateMessage(mobieNumber, JSMSTemplate.PAYDEPOSIT);
	}
	
	public boolean validateRegisterSMSCode(String mobileNumber, String code){
		return validateSMSCode(JSMSTemplate.REGISTER, mobileNumber, code);
	}
	
	public boolean validateWithDrawSMSCode(String mobileNumber, String code){
		return validateSMSCode(JSMSTemplate.WITHDRAW, mobileNumber, code);
	}
	
	public boolean validatePaydepositSMSCode(String mobileNumber, String code){
		return validateSMSCode(JSMSTemplate.PAYDEPOSIT, mobileNumber, code);
	}
	
	public boolean sendPayWithdrawMessage(String mobileNumber, java.util.Date date, Float amount){
		String[] params = new String[]{dateFormat.format(date), numberFormat.format(amount)};
		return sendTemplateMessage(mobileNumber, JSMSTemplate.PAY_WITHDRAW, params);
	}
	
	private boolean sendTemplateMessage(String mobileNumber, JSMSTemplate template, String[] params){
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
		
		return false;
		
	}
	
	private boolean sendValidateMessage(String mobieNumber, JSMSTemplate template){
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
		
		return false;
	}
	
	private boolean validateSMSCode(JSMSTemplate template,String mobileNumber, String code){
		String msgId = msgIdMap.get(template).get(mobileNumber);
		if(null==msgId){
			LOG.error(mobileNumber + "未验证");
			return false;
		}
		
		try {
			ValidSMSResult validSMSResult = jsmsClient.sendValidSMSCode(msgId, code);
			if(validSMSResult.getIsValid()){
				msgIdMap.get(template).remove(mobileNumber);
				return true;
			}
			return false;
		} catch (APIConnectionException | APIRequestException e) {
			handleException(e);
		}
		
		return false;
	}
	
	private void handleException(Exception e){
		if(e instanceof APIConnectionException){
			LOG.error("Connection error. Should retry later. ", e);
			LOG.info(e.getMessage());
		}else if (e instanceof APIRequestException) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + ((APIRequestException) e).getStatus());
            LOG.info("Error Message: " + e.getMessage() + ((APIRequestException)e).getErrorMessage());
		}else {
			LOG.error("other exception. ",e);
		}
	}
	
	private enum JSMSTemplate{
		REGISTER(47091, JSMSTemplateType.VALIDATE, new String[]{"code"}),
		WITHDRAW(82007, JSMSTemplateType.VALIDATE, new String[]{"code"}),
		PAYDEPOSIT(102276, JSMSTemplateType.VALIDATE,new String[]{"code"}),
		PAY_WITHDRAW(145602,JSMSTemplateType.NOTIFICATION, new String[]{"date","amount"} );
		
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
	
	private enum JSMSTemplateType{
		VALIDATE,
		NOTIFICATION,
		MARKETING
	}
}
