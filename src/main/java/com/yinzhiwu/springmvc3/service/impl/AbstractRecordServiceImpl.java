package com.yinzhiwu.springmvc3.service.impl;

import java.io.Serializable;

import com.yinzhiwu.springmvc3.entity.AbstractRecord;

public abstract class AbstractRecordServiceImpl<T extends  AbstractRecord, PK extends Serializable> extends BaseServiceImpl<T, PK>{

}
