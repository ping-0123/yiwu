package com.yinzhiwu.yiwu.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
*@Author ping
*@Time  创建时间:2017年10月16日下午8:49:06
*
*/

@Component
public class QiniuConfig {

//	@Value("${qiniu.expiredTime}")
	private long expired =3600l;
	
	@Value("${qiniu.accessKey}")
	private String accessKey;
	
	@Value("${qiniu.secretKey}")
	private String secretKey;
	
	@Value("${qiniu.bucket}")
	private String bucket;
	
	@Value("${qiniu.cdnUrl}")
	private String cdnUrl;
	
	public long getExpired() {
		return expired;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public String getBucket() {
		return bucket;
	}
	public String getCdnUrl() {
		return cdnUrl;
	}
	public void setExpired(long expired) {
		this.expired = expired;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public void setCdnUrl(String cdnUrl) {
		this.cdnUrl = cdnUrl;
	}
	
	
}
