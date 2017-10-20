package com.yinzhiwu.yiwu.controller.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.context.JJWTConfig;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.DistributerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年10月19日下午4:29:07
*
*/


@RequestMapping(value="/api")
@RestController
@Api("微信端客户登录模块")
public class CustomerLoginController {
	
	@Autowired private DistributerService distributerService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="/login")
	public YiwuJson login(
			@ApiParam(name="openId", value="微信openId", required=true) String openId){
		
		if(openId==null || openId.trim().length() ==0){
			return YiwuJson.createByErrorMessage("请传入openId");
		}
		
		Distributer distributer = distributerService.findByWechatNo(openId);
		if(distributer==null){
			return YiwuJson.createByErrorMessage("用户尚未注册");
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, (int) JJWTConfig.lifeCycleInSeconds);
		String token = Jwts.builder().setExpiration(calendar.getTime())
			.setSubject(distributer.getName())
			.claim(Constants.CURRENT_DISTRIBUTER_ID, distributer.getId())
			.signWith(SignatureAlgorithm.HS256,JJWTConfig.secretKey)
			.compact();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("token", token);
		return YiwuJson.createBySuccess(map);
	}
}
