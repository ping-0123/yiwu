package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.util.GeneratorUtil;
import com.yinzhiwu.yiwu.util.SecurityUtil;
import com.yinzhiwu.yiwu.util.ShareCodeUtil;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;

@Repository
public class DistributerDaoImpl extends BaseDaoImpl<Distributer, Integer> implements DistributerDao {

	@Override
	public Integer save(Distributer entity) {
		entity.init();
		
		//与Id有关的初始化
		int id = getNextId();
		if(logger.isDebugEnabled())
			logger.debug("new Id for Distributer is : " + id);
		entity.setId(id);
		entity.setPassword(SecurityUtil.encryptByMd5(entity.getPassword()));
		String memberCard = GeneratorUtil.generateMemberId(id);
		if(entity.getMemberCard() == null || "".equals(entity.getMemberCard().trim())){
			entity.setMemberCard(memberCard);
			entity.getCustomer().setMemberCard(memberCard);
			if(! StringUtils.hasLength(entity.getCustomer().getName()))
				entity.getCustomer().setName(memberCard);
		}
		entity.setShareCode(ShareCodeUtil.toSerialCode(id));
		
		if(!StringUtils.hasLength(entity.getUsername()))
			entity.setUsername(memberCard);
		if(!StringUtils.hasLength(entity.getNickName()))
			entity.setNickName(memberCard);
		if(!StringUtils.hasLength(entity.getName()))
			entity.setName(memberCard);
		
		return super.save(entity);
	}

	
	private int getNextId() {
		String sql = "SELECT  AUTO_INCREMENT FROM information_schema.tables WHERE table_name ='yiwu_distributer'";
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Integer> ints = getSession().createNativeQuery(sql)
				.addScalar("AUTO_INCREMENT", IntegerType.INSTANCE)
				.getResultList();
		return ints.get(0).intValue();
	}

	@Override
	public Distributer findByShareCode(String shareCode){
		List<Distributer> distributers = findByProperty("shareCode", shareCode);
		if(distributers.size() > 0 )
			return distributers.get(0);
		return null;
	}

	@Override
	public Distributer findByPhoneNo(String phoneNo) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("phoneNo", phoneNo);
		if (distributers.size() > 0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(), "phoneNo", phoneNo);
	}

	@Override
	public Distributer findByMemberCard(String memberCard) throws DataNotFoundException {
		return findOneByProperty("memberCard", memberCard);
	}

	@SuppressWarnings("unchecked")
	@Override
	public float getBeatRate(float exp) {
		String hql = "select count(*) from Distributer d where d.exp < :exp";
		logger.debug("传入的经验值是：" + exp);
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "exp", exp);
		logger.debug(exp + "击败的数量：" + counts.get(0).intValue());
		int sum = findCount().intValue();
		logger.debug("分销人数总数：" + sum);
		if (sum == 0)
			return 0;
		else
			return counts.get(0).intValue() / (float) sum;
	}

	@Override
	public Distributer findByWechat(String wechatNo) throws DataNotFoundException{
		return findOneByProperty("wechatNo", wechatNo);
	}

	@Override
	public Distributer findByAccountPassword(String account, String password) throws Exception {
		String encriptedPassword = SecurityUtil.encryptByMd5(password);
		List<Distributer> distributers = findByProperties(new String[] { "account", "password" },
				new Object[] { account, encriptedPassword });
		if (distributers.size() > 0)
			return distributers.get(0);
		else {
			List<Distributer> distributers2 = findByProperty("account", account);
			if (distributers2.size() > 0)
				throw new Exception("password is Incorrect");
			else
				throw new Exception("account:" + account + " is not exist");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Distributer> findTopThree() {
		String hql = "from Distributer order by accumulativeBrokerage desc";
		getHibernateTemplate().setMaxResults(3);
		return (List<Distributer>) getHibernateTemplate().find(hql);
	}

	@Override
	public int findCountByPhoneNo(String phoneNo) {
		return findCountByProperty("phoneNo", phoneNo).intValue();
	}

	@Override
	public int findCountByWechatNo(String wechatNo) {
		return findCountByProperty("wechatNo", wechatNo).intValue();
	}

	@Override
	public Distributer findByCustomerId(Integer customerId) throws DataNotFoundException {
		return findOneByProperty("customer.id", customerId);
	}

	@Override
	public PageBean<CustomerDto> findDtoPageByDistributerByKey(List<Integer> storeIds, List<Integer> employeeIds,
			List<Integer> distributerIds, String key, int pageNo, int pageSize) {
		int quantity  = 0;
		if(employeeIds != null && employeeIds.size() > 0)
			quantity++;
		if(storeIds !=null && storeIds.size()> 0)
			quantity++;
		if(distributerIds !=null && distributerIds.size()> 0)
			quantity++;
		String[] properties = new String[quantity];
		Object[] values = new Object[quantity];
		
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT new com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto");
		hql.append("(t1.customer.id, t1.name, t1.phoneNo)");
		hql.append(" FROM Distributer t1");
		hql.append(" WHERE ");
		if(key!=null && key.trim().length() > 0){
			hql.append(" (t1.name like '%"  + key + "%'");
			hql.append(" OR t1.phoneNo like '%" + key + "%')");
			hql.append(" AND");
		}
		if(quantity > 0){
			int i = 0;
			hql.append("(1=0");
			if(distributerIds != null && distributerIds.size()> 0){
				hql.append(" OR t1.superDistributer.id in :superIds");
				properties[i] = "superIds";
				values[i] = distributerIds;
				i++;
			}
			if(employeeIds != null && employeeIds.size()> 0){
				hql.append(" OR t1.server.id in :serverIds");
				properties[i] = "serverIds";
				values[i] = employeeIds;
				i++;
			}
			if(storeIds != null && storeIds.size()> 0){
				hql.append(" OR t1.followedByStore.id in :storeIds");
				properties[i] = "storeIds";
				values[i] = storeIds;
				i++;
			}
			
			hql.append(")");
		}
		

		return super.findPageByHqlWithParams(hql.toString(), 
				properties,
				values,
				pageNo,
				pageSize);
	}

	@Override
	public List<Integer> findIdsByemployees(List<Integer> employeeIds) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT DISTINCT d.id");
		hql.append(" FROM Distributer d");
		hql.append(" WHERE d.server.id in :serverIds");
		List<Integer> list = getSession().createQuery(hql.toString(), Integer.class)
				.setParameter("serverIds", employeeIds)
				.getResultList();
		if(list == null)
			list = new ArrayList<>();
		return list;
	}


	@Override
	public CustomerYzw findCustomerByWechat(String wechatNo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT t1.customer");
		hql.append(" FROM Distributer t1");
		hql.append(" WHERE t1.wechatNo = :wechatNo");
		
		List<CustomerYzw> customers = getSession().createQuery(hql.toString(),CustomerYzw.class)
				.setParameter("wechatNo", wechatNo)
				.getResultList();
		return (customers.size()> 0)?customers.get(0):null;
	}

}
