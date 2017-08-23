package com.yinzhiwu.yiwu.web.wechat;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.util.SecurityUtil;

/**
*@Author ping
*@Time  创建时间:2017年8月21日下午9:43:02
*
*/

@RestController
@RequestMapping("/wechat")
public class WechatController {

	private static final String TOKEN = "wildghost";
	
	@GetMapping
	public String validate(
	HttpServletRequest request)
	{
		String echostr =request.getParameter("echostr");
		System.out.println("echostr is:" + echostr);	
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
