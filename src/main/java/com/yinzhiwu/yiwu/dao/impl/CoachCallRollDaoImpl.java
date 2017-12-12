
package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.common.entity.search.PropertySpecification;
import com.yinzhiwu.yiwu.common.entity.search.SearchOperator;
import com.yinzhiwu.yiwu.dao.CoachCallRollDao;
import com.yinzhiwu.yiwu.entity.yzw.CoachCallRoll;

/**
 * @author ping
 * @date 2017年12月12日下午1:51:44
 * @since v2.2.0
 *	
 */

@Repository
public class CoachCallRollDaoImpl extends BaseDaoImpl<CoachCallRoll,Integer>  implements CoachCallRollDao{

	/* (non-Javadoc)
	 * @see com.yinzhiwu.yiwu.dao.CoachCallRollDao#countOfcalled(java.lang.Integer)
	 */
	@Override
	public long countOfcalled(Integer lessonId) {
		return count(Specifications.where(new PropertySpecification<CoachCallRoll>("lesson.id", SearchOperator.eq, lessonId))
				.and(new PropertySpecification<CoachCallRoll>("isCalled", SearchOperator.eq, true)));
	}
	
}
