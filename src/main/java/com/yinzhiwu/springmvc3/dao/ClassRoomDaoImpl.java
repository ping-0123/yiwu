package com.yinzhiwu.springmvc3.dao;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.impl.BaseDaoImpl;
import com.yinzhiwu.springmvc3.entity.ClassRoom;

@Repository
public class ClassRoomDaoImpl extends BaseDaoImpl<ClassRoom, String> implements ClassRoomDao {

	@Override
	public ClassRoom findById(String id) {
		return get(id);
	}

}
