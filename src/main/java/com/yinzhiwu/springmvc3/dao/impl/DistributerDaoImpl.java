package com.yinzhiwu.springmvc3.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.DistributerDao;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.util.IdGeneratorUtil;
import com.yinzhiwu.springmvc3.util.ShareCodeUtil;

@Repository
public class DistributerDaoImpl extends BaseDaoImpl<Distributer, Integer> implements DistributerDao {
	
	private static final Log logger = LogFactory.getLog(DistributerDaoImpl.class);

	@Override
	public Integer save(Distributer entity) {
		int id =  generateId();
		logger.info(id);
//		entity.setId(id);
		entity.setShareCode(ShareCodeUtil.toSerialCode(id));
		entity.setMemberId(IdGeneratorUtil.generateMemberId(id));
		saveOrUpdate(entity);
		return entity.getId();
	}

	private int generateId() {
		String hql = "select ifnull(max(id),0) + 1 from Distributer";
		List<?> ids = getHibernateTemplate().find(hql);
//		logger.info(ids.size());
		return (int) ids.get(0);
	}

	

}
