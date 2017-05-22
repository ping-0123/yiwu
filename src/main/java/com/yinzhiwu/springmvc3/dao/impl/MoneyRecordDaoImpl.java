package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

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

	@Override
	public int findCountByBeneficiatyId(int beneficiatyId) {
		return findCountByProperty("beneficiaty.id", beneficiatyId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MoneyRecord> findByTypesByBeneficiaryId(int benificiaryId, List<Integer> typeIds) {
		String hql = "from MoneyRecord where beneficiaty.id = " + benificiaryId + " and recordType.id in :typeIds";
		return (List<MoneyRecord>) getHibernateTemplate().findByNamedParam(hql, "typeIds", typeIds);
	}


}
