package com.yinzhiwu.yiwu.service;

import java.util.List;

import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.SumRecordApiView;

public interface BusinessService {

	YiwuJson<SumRecordApiView> getPerformance(int distributerId);

	YiwuJson<List<SumRecordApiView>> getTopThreePerfomance();


}
