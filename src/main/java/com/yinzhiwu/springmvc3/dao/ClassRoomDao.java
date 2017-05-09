package com.yinzhiwu.springmvc3.dao;

import com.yinzhiwu.springmvc3.entity.ClassRoom;

public interface ClassRoomDao extends IBaseDao<ClassRoom, String> {

	public ClassRoom findById(String id);
}
