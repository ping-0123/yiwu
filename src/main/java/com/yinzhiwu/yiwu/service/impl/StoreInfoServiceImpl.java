package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.StoreInfoDao;
import com.yinzhiwu.yiwu.entity.StoreInfo;
import com.yinzhiwu.yiwu.service.StoreInfoService;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午2:43:51
*
*/

@Service
public class StoreInfoServiceImpl extends BaseServiceImpl<StoreInfo, Integer> implements StoreInfoService {
	
	@Autowired public void setBaseDao(StoreInfoDao storeInfoDao){
		super.setBaseDao(storeInfoDao);
	}
	
}
