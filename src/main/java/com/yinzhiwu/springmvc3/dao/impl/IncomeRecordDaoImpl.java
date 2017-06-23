package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.IncomeRecordDao;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.model.view.IncomeRecordApiView;

@Repository
public class IncomeRecordDaoImpl extends BaseDaoImpl<IncomeRecord, Integer> implements IncomeRecordDao {

	@Override
	public int findCountByIncomeTypesByBeneficiary(int benificiaryId, int[] incomeTypeIds) {
		if(incomeTypeIds.length == 0) return 0;
		List<Integer> ints = new ArrayList<>();
		for (int i : incomeTypeIds) {
			ints.add(i);
		}
		
		StringBuilder builder = new StringBuilder("select count(*) from IncomeRecord t1 ");
		builder.append("where t1.benificiary.id = :benificiaryId and t1.incomeType.id in :incomeTypeIds");
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(builder.toString(),
				new String[]{"benificiaryId", "incomeTypeIds"}, 
				new Object[]{benificiaryId, ints});
		return counts.get(0).intValue();
	}

	@Override
	public IncomeRecord findExpProducedByEvent(Integer id, IncomeType exp) {
		try{
			List<IncomeRecord> records = findByProperties(
					new String[]{"incomeEvent.id", "incomeType.id"},
					new Object[]{id, exp.getId()});
			return records.get(0);
		}catch (Exception e) {
			return null;
		}

	}

	
	@Override
	public IncomeRecordApiView findApiViewById(int id){
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT new com.yinzhiwu.springmvc3.model.view.IncomeRecordApiView(");
		builder.append(" t1.id");
		builder.append(",t1.recordTimestamp");
		builder.append(",t1.incomeEvent.type.name");
		builder.append(",t1.contributor.name");
		builder.append(",t1.contributor.memberId");
		builder.append(",t1.contributor.superDistributer.name");
		builder.append(",t1.incomeType.name");
		builder.append(",t1.incomeValue");
		builder.append(",t1.contributedValue");
		builder.append(",t1.currentValue");
		builder.append(",t1.incomeFactor");
		builder.append(") FROM IncomeRecord t1 ");
		builder.append(" WHERE t1.id=:incomeRecordId");
		return getSession().createQuery(builder.toString(), IncomeRecordApiView.class)
				.setParameter("incomeRecordId", id,IntegerType.INSTANCE)
				.getSingleResult();
	}

	@Override
	public List<IncomeRecordApiView> getListFaster(int observerId, int eventTypeId, int relationTypeId,
			int incomeTypeId) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT new com.yinzhiwu.springmvc3.model.view.IncomeRecordApiView(");
		builder.append(" t1.id");
		builder.append(",t1.recordTimestamp");
		builder.append(",t1.incomeEvent.type.name");
		builder.append(",t1.contributor.name");
		builder.append(",t1.contributor.memberId");
		builder.append(",t1.contributor.superDistributer.name");
		builder.append(",t1.incomeType.name");
		builder.append(",t1.incomeValue");
		builder.append(",t1.contributedValue");
		builder.append(",t1.currentValue");
		builder.append(",t1.incomeFactor");
		builder.append(") FROM IncomeRecord t1 ");
		builder.append(" WHERE 1=1");
		if(observerId != -1)
			builder.append(" AND t1.benificiary.id =" + observerId);
		if(eventTypeId != -1)
			builder.append(" AND t1.incomeEvent.type.id =" + eventTypeId );
		if(relationTypeId != -1)
			builder.append(" AND t1.con_ben_relation.id =" + relationTypeId);
		if(incomeTypeId != -1)
			builder.append(" AND t1.incomeType.id = " + incomeTypeId);
		return getSession().createQuery(builder.toString(), IncomeRecordApiView.class)
				.getResultList();
	}
	
	@Override
	public void testCriteriaQuery(){
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<IncomeRecord> root = criteria.from(IncomeRecord.class);
		criteria.select(root.get("incomeValue"));
		criteria.where(builder.equal(root.get("contributedValue"), 1));
		List<Long> records = getSession().createQuery(criteria).getResultList();
		getHibernateTemplate().findByCriteria((DetachedCriteria) criteria);
		System.out.println(records.size());
	}
}
