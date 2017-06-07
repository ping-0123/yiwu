package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.util.GeneratorUtil;
import com.yinzhiwu.springmvc3.util.SecurityUtil;
import com.yinzhiwu.springmvc3.util.ShareCodeUtil;

@Repository
public class DistributerDaoImpl extends BaseDaoImpl<Distributer, Integer> implements DistributerDao {
	
	private static final Log logger = LogFactory.getLog(DistributerDaoImpl.class);

	@Override
	public Integer save(Distributer entity){
		int id = getNextId();
		logger.debug(id);
		entity.setId(id);
		entity.setPassword(SecurityUtil.encryptByMd5(entity.getPassword()));
		entity.setMemberId(GeneratorUtil.generateMemberId(id));
		entity.setShareCode(ShareCodeUtil.toSerialCode(id));
		return super.save(entity);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private int getNextId() {
		String sql = "select  AUTO_INCREMENT from information_schema.tables where table_name ='yiwu_distributer'";
		List<Long> list = getSession().createNativeQuery(sql).addScalar("AUTO_INCREMENT", LongType.INSTANCE) .list();
		return list.get(0).intValue();
	}

	@Override
	public Distributer findByShareCode(String shareCode) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("shareCode", shareCode);
		if(distributers.size()>0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(),"shareCode",shareCode);
	}

	@Override
	public Distributer findByPhoneNo(String phoneNo) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("phoneNo", phoneNo);
		if(distributers.size()>0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(),"phoneNo",phoneNo);
	}

	@Override
	public Distributer findByMemberId(String memeberId) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("memeberId", memeberId);
		if(distributers.size()>0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(),"memeberId",memeberId);
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
		if (sum==0)
			return 0;
		else
			return counts.get(0).intValue()/(float)sum;
	}

	@Override
	public Distributer findByWechat(String wechatNo) throws DataNotFoundException {
		List<Distributer> distributers = findByProperty("wechatNo", wechatNo);
		if(distributers.size()>0)
			return distributers.get(0);
		else
			throw new DataNotFoundException(this.getClass(),"wechatNo",wechatNo);
	}

	@Override
	public Distributer findByAccountPassword(String account, String password) throws Exception {
		String encriptedPassword = SecurityUtil.encryptByMd5(password);
		List<Distributer> distributers = findByProperties(
				new String[]{"account", "password"}, 
				new Object[]{account,encriptedPassword});
		if (distributers.size()>0)
			return distributers.get(0);
		else{
			List<Distributer> distributers2 = findByProperty("account", account);
			if(distributers2.size()>0)
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


	

}
