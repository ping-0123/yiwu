package com.yinzhiwu.yiwu.context;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午9:56:51
*
*/

@Component
public class JpushConfig {
	
	@Value("${jpush.wechat.appName}")
	private String appName;
	
	@Value("${jpush.wechat.appKey}")
	private String appKey;
	
	@Value("${jpush.wechat.masterSecret}")
	private String masterSecret;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}
	
	
	
}
