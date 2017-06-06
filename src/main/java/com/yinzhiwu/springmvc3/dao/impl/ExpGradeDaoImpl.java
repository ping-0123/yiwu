package com.yinzhiwu.springmvc3.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.ExpGradeDao;
import com.yinzhiwu.springmvc3.entity.ExpGrade;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;


@Repository
public class ExpGradeDaoImpl  extends BaseDaoImpl<ExpGrade, Integer> implements ExpGradeDao {
	
	private static final Log LOG = LogFactory.getLog(ExpGradeDaoImpl.class);
	
	@Override
	public ExpGrade findLowestGrade() {
		try {
			return get(4000014);
		} catch (DataNotFoundException e) {
			LOG.error("请插入原始数据");
			e.printStackTrace();
			return null;
		}
	}

}
