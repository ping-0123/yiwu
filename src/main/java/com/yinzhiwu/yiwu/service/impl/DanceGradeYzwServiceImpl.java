package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.DanceGradeYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.DanceGradeYzw;
import com.yinzhiwu.yiwu.service.DanceGradeYzwService;

/**
*@Author ping
*@Time  创建时间:2017年10月31日下午3:43:46
*
*/

@Service
public class DanceGradeYzwServiceImpl extends BaseServiceImpl<DanceGradeYzw,Integer> implements DanceGradeYzwService{
	
	@Autowired public void setBaseDao(DanceGradeYzwDao dao){super.setBaseDao(dao);}
}
