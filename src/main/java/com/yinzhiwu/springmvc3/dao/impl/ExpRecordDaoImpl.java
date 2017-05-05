package com.yinzhiwu.springmvc3.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ExpRecordDao;
import com.yinzhiwu.springmvc3.entity.ExpRecord;

@Repository
public class ExpRecordDaoImpl extends BaseDaoImpl<ExpRecord, Integer> implements ExpRecordDao {
	
	@Override
	public int findMyShareTweetTimes(int beneficiary_id){
		return findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000011});
	}
	
	@Override
	public int findSubordinateShareTweetTimes(int beneficiary_id){
		return findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000012});
	}
	
	@Override
	public int findSubordinateRegisterTimes(int beneficiary_id){
		return findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000001});

	}
	
	@Override
	public int findSecondaryRegisterTimes(int beneficiary_id){
		return findCountByProperties(
				new String[]{"beneficiaty.id","recordType.id"},
				new Object[]{beneficiary_id,17000002});
	}
	
}
