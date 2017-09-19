package com.yinzhiwu.yiwu.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.StoreManCallRollYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.StoreManCallRollYzw;

/**
*@Author ping
*@Time  创建时间:2017年8月1日下午6:51:19
*
*/

@Repository
public class StoreManCallRollYzwDaoImpl extends BaseDaoImpl<StoreManCallRollYzw, Integer>
	implements StoreManCallRollYzwDao
{

	@Override
	public Long findCountByLessonIdAndCallRolledFlag(String string, boolean b) {
		return findCountByProperties(new String[]{"lessonId", "callRolled"}, 
				new Object[]{string,b});
	}

}
