package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzw.StoreManCallRollYzw;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午6:50:07
*
*/

public interface StoreManCallRollYzwDao  extends IBaseDao<StoreManCallRollYzw, Integer>{

	Long findCountByLessonIdAndCallRolledFlag(String string, boolean b);

	/**
	 * @param lessonId
	 * @return
	 */
	public long countOfCalled(Integer lessonId);

}
