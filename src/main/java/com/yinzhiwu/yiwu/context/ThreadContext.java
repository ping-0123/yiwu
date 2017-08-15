package com.yinzhiwu.yiwu.context;

import java.util.HashMap;
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
		_getSureMap().put(Constants.CURRENT_USER, user);
	}
	
	public static Distributer getUser(){
		return (Distributer) _getSureMap().get(Constants.CURRENT_USER);
	}
	
	public static void setDistributer(DistributerApiView view){
		_getSureMap().put(Constants.CURRENT_DISTRIBUTER_VIWE, view);
	}
	public static DistributerApiView getDistributer(){
		return (DistributerApiView) _getSureMap().get(Constants.CURRENT_DISTRIBUTER_VIWE);
	}
	
	private static Map<String, Object> _getSureMap(){
		if(tl.get() == null)
			tl.set(new HashMap<>());
		return tl.get();
	}
}
