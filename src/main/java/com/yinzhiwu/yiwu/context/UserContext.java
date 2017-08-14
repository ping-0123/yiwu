package com.yinzhiwu.yiwu.context;

import com.yinzhiwu.yiwu.entity.Distributer;

/**
*@Author ping
*@Time  创建时间:2017年7月13日下午1:35:57
*
*/

public class UserContext {

	private static ThreadLocal<Distributer> tl = new ThreadLocal<>();
	
	public static void setUser(Distributer user){
		tl.set(user);
	}
	
	public static Distributer getUser(){
		return tl.get();
	}
}
