package com.test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.enterprise.inject.New;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yinzhiwu.springmvc3.qiniu.Qiniu;

@RunWith(BlockJUnit4ClassRunner.class)
public class QiniuTest {
	
	private String accessKey = Qiniu.ACCESS_KEY;
	private String secretKey = Qiniu.SECRET_KEY;
	private String bucket = Qiniu.BUCKET;
	private Zone zone = Zone.zone2();
	private Configuration cfg = new Configuration(zone);
	private UploadManager uploadManager = new UploadManager(cfg);
	private Auth auth = Auth.create(accessKey, secretKey);
	private String upToken = auth.uploadToken(bucket);
	
	private String filePath ="C:\\Users\\ping\\Pictures\\yiwu测试\\android学员端闪退.jpg";
	private String sqlFilePath = "C:\\Users\\ping\\Documents\\asas.sql";
	private String sampleVideo = "C:\\Users\\Public\\Videos\\Sample Videos\\Wildlife.wmv";
	private String wugui="C:\\Users\\ping\\Videos\\wugui.mp4";
	
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
		
		String key = "android/exit.jpg";
		
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
	
	@Test
	public void uploadBytes(){
		String key = "bytes";
		try {
			byte[] uploadBytes = "杭州音之舞科技有限公司".getBytes("utf-8");
			Response response = uploadManager.put(uploadBytes, key, upToken);
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);	
			System.out.println(putRet.hash);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (QiniuException e) {
			Response r = e.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void uploadInputStream(){
		String key = "yzw2\\inputStream";
		try {
			byte[] uploadBytes = "杭州音之舞科技有限公司".getBytes("utf-8");
			ByteArrayInputStream stream = new ByteArrayInputStream(uploadBytes);
//			Response response = uploadManager.put(uploadBytes, key, upToken);
			Response response = uploadManager.put(stream, key, upToken, null, null);
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);	
			System.out.println(putRet.hash);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (QiniuException e) {
			Response r = e.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void getFileStatus(){
		String key="video/wugui.mp4";
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			FileInfo info = bucketManager.stat(bucket, key);
			System.out.println(info.hash);
			System.out.println(info.fsize);
			System.out.println(info.mimeType);
			System.out.println(new Date(info.putTime));
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
