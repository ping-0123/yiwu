package com.yinzhiwu.springmvc3.dao;

import java.util.List;

import com.yinzhiwu.springmvc3.entity.yzw.CheckInsYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.LessonYzw;

public interface CheckInsYzwDao extends IBaseDao<CheckInsYzw, Integer>{

	int findCountByContractNos(List<String> contractNos);

	List<LessonYzw> findByContractNos(List<String> contractNos);

	int findCountByCustomerId(int customerId);

	boolean isCheckedIn(CustomerYzw customer, LessonYzw lesson);



}
