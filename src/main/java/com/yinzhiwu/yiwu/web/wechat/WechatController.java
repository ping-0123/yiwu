package com.yinzhiwu.yiwu.web.wechat;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.util.SecurityUtil;

/**
*@Author ping
*@Time  创建时间:2017年8月21日下午9:43:02
*
*/

@RestController
public class WechatController {

	private static final String TOKEN = "wildghost";
	
	@GetMapping(value="/wechat")
	public String validate(String signature, String timestamp, String nonce, String echostr){
		if(_checkSignature(signature, timestamp, nonce))
			return echostr;
		return echostr;
	}
	
	private boolean _checkSignature(String signature, String timestamp, String nonce){
		String[] arr = new String[]{TOKEN, timestamp, nonce};
		//1.排序
		Arrays.sort(arr);
		//2.生成字符串
		StringBuilder content = new StringBuilder();
		for(int i=0; i<arr.length; i++){
			content.append(arr[i]);
		}
		//3.sha1加密
		String encriptContent = SecurityUtil.encryptBySHA1(content.toString());
		//4.与signature对比
		return encriptContent.equals(signature);
	}
}
