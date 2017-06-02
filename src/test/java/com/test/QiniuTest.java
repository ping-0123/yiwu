package com.test;

import javax.xml.ws.RespectBinding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yinzhiwu.springmvc3.qiniu.Qiniu;

@RunWith(BlockJUnit4ClassRunner.class)
public class QiniuTest {
	
	private String accessKey = Qiniu.ACCESS_KEY;
	private String secretKey = Qiniu.SECRET_KEY;
	private String bucket = Qiniu.BUCKET;
	private Zone zone = Zone.zone2();
	
	private String filePath ="C:\\Users\\ping\\Pictures\\yiwu测试\\android学员端闪退.jpg";

	@Test
	public void testReturnBody(){
		Auth auth = Auth.create(Qiniu.ACCESS_KEY, Qiniu.ACCESS_KEY);
		StringMap putPolicy = new StringMap();
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		long expireSeconds = 3600;
		String upToken = auth.uploadToken(Qiniu.BUCKET, null, expireSeconds, putPolicy);
		System.out.println(upToken);
	}
	
	
	@Test
	public void uploadFromLocalFile(){
		Configuration cfg = new Configuration(zone);
		
		UploadManager uploadManager = new UploadManager(cfg);
		
		String key = "android";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try{
			Response response = uploadManager.put(filePath, key, upToken);
			
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		}catch (QiniuException e) {
			Response r = e.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}
	}
}
