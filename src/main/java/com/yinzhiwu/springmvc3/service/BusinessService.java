package com.yinzhiwu.springmvc3.service;

import java.util.List;

import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.SumRecordApiView;

public interface BusinessService {

	YiwuJson<SumRecordApiView> getPerformance(int distributerId);

	YiwuJson<List<SumRecordApiView>> getTopThreePerfomance();


}
