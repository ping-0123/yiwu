package com.yinzhiwu.yiwu.qiniu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Qiniu {


	private static Log LOG = LogFactory.getLog(Qiniu.class);
	
	public static long EXPIRED_TIME = 0;
	
	public static  String ACCESS_KEY ;
	
	public static  String SECRET_KEY ;
	
	public static  String BUCKET ;
	
	public static  String CDN_URL;
	
	static{
		Properties properties = new Properties();
		InputStream in;
		try {
//			in = new FileInputStream(new File("qiniu.properties"));
			in = Qiniu.class.getClassLoader().getResourceAsStream("properties/qiniu.properties");
//			in = ClassLoader.getSystemResourceAsStream("qiniu.properties");
			properties.load(in);
			ACCESS_KEY = properties.getProperty("qiniu.accessKey");
			SECRET_KEY = properties.getProperty("qiniu.secretKey");
			BUCKET = properties.getProperty("qiniu.bucket");
			CDN_URL = properties.getProperty("qiniu.cdnUrl");
			EXPIRED_TIME=Long.valueOf(properties.getProperty("qiniu.expiredTime"));
			in.close();
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}finally {
		}
		
	}
	
}
