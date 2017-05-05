package com.yinzhiwu.springmvc3.service;

import com.yinzhiwu.springmvc3.model.SumRecordApiView;
import com.yinzhiwu.springmvc3.model.YiwuJson;

public interface BusinessService {

	YiwuJson<SumRecordApiView> getPerformance(int distributerId);

}
