package com.yinzhiwu.yiwu.context;

import java.util.Map;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;

/**
*@Author ping
*@Time  创建时间:2017年7月13日下午1:35:57
*
*/

public class ThreadContext {

	private static ThreadLocal<Map<String, Object>> tl = new ThreadLocal<>();
	
	public static void setUser(Distributer user){
		tl.get().put(Constants.CURRENT_USER, user);
	}
	
	public static Distributer getUser(){
		return (Distributer) tl.get().get(Constants.CURRENT_USER);
	}
	
	public static void setDistributer(DistributerApiView view){
		tl.get().put(Constants.CURRENT_DISTRIBUTER_VIWE, view);
	}
	public static DistributerApiView getDistributer(){
		return (DistributerApiView) tl.get().get(Constants.CURRENT_DISTRIBUTER_VIWE);
	}
	
}
