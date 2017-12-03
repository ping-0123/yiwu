package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.PayedMethodDao;
import com.yinzhiwu.yiwu.entity.yzw.PayedMethodYzw;
import com.yinzhiwu.yiwu.service.PayedMethodService;

/**
* @author 作者 ping
* @Date 创建时间：2017年12月4日 上午3:02:36
*
*/

@Service
public class PayedMethodServiceImpl extends BaseServiceImpl<PayedMethodYzw,Integer> implements PayedMethodService{
	
	@Autowired public void setBaseDao(PayedMethodDao dao){
		super.setBaseDao(dao);
	}
}
