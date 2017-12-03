package com.yinzhiwu.yiwu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.dao.ElectricContractTypeDao;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractTypeYzw;
import com.yinzhiwu.yiwu.service.ElectricContractTypeService;

/**
* @author 作者 ping
* @Date 创建时间：2017年12月3日 下午4:36:41
*
*/


@Service
public class ElectrictContractTypeServiceImpl extends BaseServiceImpl<ElectricContractTypeYzw,Integer> implements ElectricContractTypeService{
	
	@Autowired public void setBaseDao(ElectricContractTypeDao dao){super.setBaseDao(dao);}
}
