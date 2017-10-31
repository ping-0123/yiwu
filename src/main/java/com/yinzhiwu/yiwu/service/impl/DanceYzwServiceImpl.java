package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DanceYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DanceYzw;
import com.yinzhiwu.yiwu.service.DanceYzwService;

/**
*@Author ping
*@Time  创建时间:2017年10月31日下午3:37:50
*
*/

@Service
public class DanceYzwServiceImpl extends BaseServiceImpl<DanceYzw,String> implements DanceYzwService{
	
	@Autowired public void setBaseDao(DanceYzwDao dao){super.setBaseDao(dao);}
}
