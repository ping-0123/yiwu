package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.DataNotFoundException;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.impl.BaseDaoImpl;
import com.yinzhiwu.springmvc3.entity.ExpGrade;


@Repository
public class ExpGradeImpl  extends BaseDaoImpl<ExpGrade, Integer> implements ExpGradeDao {
	
	private static final Log logger = LogFactory.getLog(ExpGradeImpl.class);
	
	@Override
	public ExpGrade findLowestGrade() {
		List<ExpGrade> expGrades = findByProperty("lowestGrade", true);
		if(expGrades.size()==0){
			logger.error("pleae insert or mark a lowest exp grade  in table expgrade" );
			try {
				throw new DataNotFoundException(ExpGradeImpl.class, "lowestGrade", true);
			} catch (DataNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(expGrades.size()>1){
			logger.warn("mutlple lowest exp grade found in table expgrade");
		}
		return expGrades.get(0);
	}

}
