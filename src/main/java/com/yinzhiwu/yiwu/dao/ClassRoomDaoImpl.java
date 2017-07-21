package com.yinzhiwu.yiwu.dao;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.impl.BaseDaoImpl;
import com.yinzhiwu.yiwu.entity.yzwOld.ClassRoom;

@Repository
public class ClassRoomDaoImpl extends BaseDaoImpl<ClassRoom, String> implements ClassRoomDao {

	@Override
	public ClassRoom findById(String id) {
		return getHibernateTemplate().get(ClassRoom.class, id);
	}

}
