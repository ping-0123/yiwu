package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.MoneyRecordDao;
import com.yinzhiwu.springmvc3.entity.MoneyRecord;

@Repository
public class MoneyRecordDaoImpl extends BaseDaoImpl<MoneyRecord, Integer> implements MoneyRecordDao{
	
	@Override
	public int findSubordinateOrderTimes(int beneficiary_id){
		int count2 =  findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000007});
		int count1 =  findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000005});
		
		return count1 + count2;

	}
	
	@Override
	public int findSecondaryOrderTimes(int beneficiary_id){
		int count2  =  findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000008});
		int count1  =  findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000006});

		return count1 + count2;

	}
}
