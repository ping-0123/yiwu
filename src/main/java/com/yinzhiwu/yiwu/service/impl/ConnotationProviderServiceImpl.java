package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ConnotationProviderDao;
import com.yinzhiwu.yiwu.entity.ConnotationProvider;
import com.yinzhiwu.yiwu.service.ConnotationProviderService;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:51:43
*
*/

@Service
public class ConnotationProviderServiceImpl extends BaseServiceImpl<ConnotationProvider,Integer> implements ConnotationProviderService{

	@Autowired public void setBaseDao(ConnotationProviderDao dao){super.setBaseDao(dao);}
}
