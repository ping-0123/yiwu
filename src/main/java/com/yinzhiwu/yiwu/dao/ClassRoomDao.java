package com.yinzhiwu.yiwu.dao;

import com.yinzhiwu.yiwu.entity.yzwOld.ClassRoom;

public interface ClassRoomDao extends IBaseDao<ClassRoom, String> {

	public ClassRoom findById(String id);
}
