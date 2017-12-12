
package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.CoachCallRoll;

/**
 * @author ping
 * @date 2017年12月12日下午1:51:01
 * @since v2.2.0
 *	
 */
public interface CoachCallRollDao extends IBaseDao<CoachCallRoll, Integer> {

	/**
	 * @param lessonId
	 * @return
	 */
	public long countOfcalled(Integer lessonId);

}
