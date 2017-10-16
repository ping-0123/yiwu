package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.ElectricContractYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.ElectricContractYzw;

@Repository
public class ElectricContractYzwDaoImpl extends BaseDaoImpl<ElectricContractYzw, String>
		implements ElectricContractYzwDao {

	@Override
	public String save(ElectricContractYzw econtract){
		econtract.init();
		return (String) getSession().save(econtract);
	}

}
