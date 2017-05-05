package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.util.GeneratorUtil;
import com.yinzhiwu.springmvc3.util.SecurityUtil;
import com.yinzhiwu.springmvc3.util.ShareCodeUtil;

@Repository
public class DistributerDaoImpl extends BaseDaoImpl<Distributer, Integer> implements DistributerDao {
	
	private static final Log logger = LogFactory.getLog(DistributerDaoImpl.class);

	@Override
	public Integer save(Distributer entity) {
		entity.setPassword(SecurityUtil.encryptByMd5(entity.getPassword()));
		entity.setMemberId(ShareCodeUtil.toSerialCode(1));
		int id = save(entity);
		entity.setShareCode(ShareCodeUtil.toSerialCode(id));
		entity.setMemberId(GeneratorUtil.generateMemberId(id));
		update(entity);
		return entity.getId();
	}

	@SuppressWarnings("unused")
	private int generateId() {
		String hql = "select ifnull(max(id),0) + 1 from Distributer";
		List<?> ids = getHibernateTemplate().find(hql);
//		logger.info(ids.size());
		return (int) ids.get(0);
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
		String hql = "select count(*) from Distributer d where d.exp <= :exp";
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(hql, "exp", exp);
		int sum = findCount();
		if (sum==0)
			return 0;
		else
			return counts.get(0).intValue()/sum;
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
	public Distributer findByAccountPassword(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
