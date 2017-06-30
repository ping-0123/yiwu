package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.IncomeRecordDao;
import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.entity.income.ShareTweetEvent;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.model.view.IncomeRecordApiView;
import com.yinzhiwu.springmvc3.model.view.ShareTweetIncomeRecordApiView;

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
		CriteriaQuery<IncomeRecord> criteria = builder.createQuery(IncomeRecord.class);
		Root<IncomeRecord> root = criteria.from(IncomeRecord.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get("contributedValue"), 1));
		List<IncomeRecord> records = getSession().createQuery(criteria).getResultList();
		System.out.println(records.size());
	}

	@Override
	public List<ShareTweetIncomeRecordApiView> getShareTweetRecordApiViews(int beneficiaryId, int[] eventTypeIds,
			int[] relationTypeIds, int[] incomeTypeIds) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ShareTweetIncomeRecordApiView> criteria
			= builder.createQuery(ShareTweetIncomeRecordApiView.class);
		Root<ShareTweetEvent> eventFrom = criteria.from(ShareTweetEvent.class);
		Join<ShareTweetEvent,IncomeRecord> recordJoin = eventFrom.join("incomeRecords", JoinType.LEFT);
//		Join<ShareTweetEvent, Tweet> tweetJoin = eventJoin.join("tweet", JoinType.LEFT);
		criteria.select(builder.construct(ShareTweetIncomeRecordApiView.class,
				recordJoin.get("id"),
				eventFrom.get("distributer").get("name"),
				eventFrom.get("occurTime"),
				eventFrom.get("tweet").get("tweetType").get("name"),
				eventFrom.get("tweet").get("title"),
				recordJoin.get("incomeValue")
				));
		//where
		Predicate condition = builder.equal(recordJoin.get("benificiary").get("id"), beneficiaryId);
		if(eventTypeIds !=null && eventTypeIds.length> 0){
			logger.debug(eventTypeIds[0]);
			List<Integer> types = new ArrayList<>();
			for (int id : eventTypeIds) {
				types.add(Integer.valueOf(id));
			}
			condition = builder.and(condition,recordJoin.get("incomeEvent").get("type").get("id").in(types));
		}
		if(relationTypeIds !=null && relationTypeIds.length> 0){
			logger.debug(relationTypeIds[0]);
			List<Integer> relations = new ArrayList<>();
			for (int i : relationTypeIds) {
				relations.add(Integer.valueOf(i));
			}
			condition = builder.and(condition, recordJoin.get("con_ben_relation").get("id").in(relations));
		}
		if(incomeTypeIds !=null && incomeTypeIds.length> 0){
			logger.debug(incomeTypeIds[0]);
			List<Integer> incomes = new ArrayList<>();
			for (int i : incomeTypeIds) {
				incomes.add(Integer.valueOf(i));
			}
			condition = builder.and(condition, recordJoin.get("incomeType").get("id").in(incomes));
		}
		criteria.where(condition);
		List<ShareTweetIncomeRecordApiView> records = getSession().createQuery(criteria).getResultList();
		
		return records;
	}
}
