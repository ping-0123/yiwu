package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.web.purchase.dto.StoreDto;

/**
*@Author ping
*@Time  创建时间:2017年7月28日上午9:07:48
*
*/

public interface DepartmentYzwService extends IBaseService<DepartmentYzw, Integer>{

	List<StoreDto> findAllStores(int employeeId);

	List<StoreDto> findVisableStoresByEmployee(int employeeId);

}
