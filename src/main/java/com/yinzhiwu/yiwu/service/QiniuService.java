package com.yinzhiwu.yiwu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;
import com.yinzhiwu.yiwu.context.QiniuConfig;

/**
 * 
 * @author ping
 * @date 2017年10月16日下午9:09:08
 *
 */

@Service
public class QiniuService {

	@Autowired private QiniuConfig config;
	
	public String getUrl(String uri){
		return uri==null?null:(config.getCdnUrl()+uri);
	}
	
	public String createToken(){
		Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
		return auth.uploadToken(config.getBucket());
	}
	
}
