package com.yinzhiwu.springmvc3.qiniu;

import com.qiniu.util.Auth;

public class Qiniu {

	public static String ACCESS_KEY="jz0jjNvbr20QsNzkGnxKnBOHjdBwXMeWwyizdBux";
	
	public static String SECRET_KEY="vivj5pprzc0ziUHBxNUoJ-xpHuG13I2CUX4aRC_z";
	
	public static String BUCKET ="yzw-wechat";
	
	
	public static void main(String[] args) {
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String fileKey = "file key";
		String upToken = auth.uploadToken(BUCKET, fileKey);
		System.out.println(auth.uploadToken(BUCKET));
		System.out.println(upToken);
	}
}
