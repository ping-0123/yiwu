package com.yinzhiwu.yiwu.service;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.yinzhiwu.yiwu.context.QiniuConfig;

/**
 * 
 * @author ping
 * @date 2017年10月16日下午9:09:08
 *
 */

@Service
public class QiniuServiceImpl implements FileService {
	
	private final static Log log = org.apache.commons.logging.LogFactory.getLog(QiniuServiceImpl.class);

	@Autowired private QiniuConfig config;
	private Auth auth;
	private BucketManager bucketManager;
	
	public QiniuServiceImpl(@Autowired QiniuConfig config){
		this.config = config;
		this.auth = Auth.create(config.getAccessKey(), config.getSecretKey());
		this.bucketManager  = new BucketManager(auth,new Configuration());
	}
	
	public String getUrl(String uri){
		return uri==null?null:(config.getCdnUrl()+uri);
	}
	
	@Override
	public String createAccessToken(){
		Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
		return auth.uploadToken(config.getBucket());
	}
	
	public String getUploadUrl(){
		return config.getUploadUrl();
	}

	public Object getCdnUrl() {
		return config.getCdnUrl();
	}

	@Override
	public String upload(MultipartFile file) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileUrl(String uri) {
		return uri==null?null:(config.getCdnUrl()+uri);
	}

	@Override
	public String getImageUrl(String uri) {
		return uri==null?null:(config.getCdnUrl()+uri);
	}

	@Override
	public boolean delete(String fileName) {
		try {
			bucketManager.delete(config.getBucket(), fileName);
			return true;
		} catch (QiniuException e) {
			log.error(e, e);
		}
		return false;
	}

}
