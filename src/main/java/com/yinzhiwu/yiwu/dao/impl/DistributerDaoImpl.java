package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.util.GeneratorUtil;
import com.yinzhiwu.yiwu.util.SecurityUtil;
import com.yinzhiwu.yiwu.util.ShareCodeUtil;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;

@Repository
public class DistributerDaoImpl extends BaseDaoImpl<Distributer, Integer> implements DistributerDao {

	private static final Log logger = LogFactory.getLog(DistributerDaoImpl.class);

	@Override
	public Integer save(Distributer entity) {
		int id = getNextId();
		logger.debug(id);
		entity.setId(id);
		entity.setPassword(SecurityUtil.encryptByMd5(entity.getPassword()));
		if(entity.getMemberId() == null)
			entity.setMemberId(GeneratorUtil.generateMemberId(id));
		entity.setShareCode(ShareCodeUtil.toSerialCode(id));
		return super.save(entity);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private int getNextId() {
		String sql = "select  AUTO_INCREMENT from information_schema.tables where table_name ='yiwu_distributer'";
		List<Integer> ints = getSession().createNativeQuery(sql, Integer.class)
				.getResultList();
		return ints.get(0);
	}

	@Override
	public Distributer findByShareCode(String shareCode) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("shareCode", shareCode);
		if (distributers.size() > 0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(), "shareCode", shareCode);
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
	public Distributer findByMemberId(String memeberId) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("memeberId", memeberId);
		if (distributers.size() > 0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(), "memeberId", memeberId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public float getBeatRate(float exp) {
		String hql = "select count(*) from Distributer d where d.exp < :exp";
		logger.debug("传入的经验值是：" + exp);
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "exp", exp);
		logger.debug(exp + "击败的数量：" + counts.get(0).intValue());
		int sum = findCount();
		logger.debug("分销人数总数：" + sum);
		if (sum == 0)
			return 0;
		else
			return counts.get(0).intValue() / (float) sum;
	}

	@Override
	public Distributer findByWechat(String wechatNo) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("wechatNo", wechatNo);
		if (distributers.size() > 0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(), "wechatNo", wechatNo);
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
		return findCountByProperty("phoneNo", phoneNo);
	}

	@Override
	public int findCountByWechatNo(String wechatNo) {
		return findCountByProperty("wechatNo", wechatNo);
	}

	@Override
	public Distributer findByCustomerId(Integer customerId) {
		List<Distributer> ds;
		try {
			ds = findByProperty("customer.id", customerId);
		} catch (DataNotFoundException e) {
			logger.info(e);
			return null;
		}
		return ds.get(0);
	}

	@Override
	public PageBean<CustomerDto> findDtoPageByDistributerByKey(List<Integer> storeIds, List<Integer> employeeIds,
			List<Integer> distributerIds, String key, int pageNo, int pageSize) {
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
		hql.append("(");
		if(distributerIds.size()> 0)
			hql.append(" t1.superDistributer.id in :superIds");
		if(employeeIds.size()> 0)
			hql.append(" OR t1.server.id in :serverIds");
		if(storeIds.size()> 0)
			hql.append(" OR t1.followedByStore.id in :storeIds");
		hql.append(")");
		

		return null;
	}

	@Override
	public List<Integer> findIdsByemployees(List<Integer> employeeIds) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT DISTINCT d.ids");
		hql.append(" FROM Distributer d");
		hql.append(" WHERE d.server.id in :serverIds");
		List<Integer> list = getSession().createQuery(hql.toString(), Integer.class)
				.setParameter("serverIds", employeeIds)
				.getResultList();
		if(list == null)
			list = new ArrayList<>();
		return list;
	}

}
