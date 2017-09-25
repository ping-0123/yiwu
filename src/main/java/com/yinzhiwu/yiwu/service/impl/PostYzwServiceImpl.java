package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.PostYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.service.PostYzwService;

/**
*@Author ping
*@Time  创建时间:2017年9月14日下午7:53:56
*
*/

@Service
public class PostYzwServiceImpl extends BaseServiceImpl<PostYzw,Integer> implements PostYzwService {

	@Autowired
	public void setBaseDao(PostYzwDao dao){
		super.setBaseDao(dao);
	}
	

}
