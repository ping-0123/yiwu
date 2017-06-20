package com.yinzhiwu.springmvc3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.entity.income.IncomeRecord;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.IncomeRecordApiView;
import com.yinzhiwu.springmvc3.model.view.IncomeRecordShareTweetApiView;
import com.yinzhiwu.springmvc3.service.IncomeRecordService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/incomeRecord/")
public class IncomeRecordController extends BaseController{
	
	@Autowired private IncomeRecordService incomeRecordService;
	
	
	@GetMapping("/list")
	@ApiOperation(value="获取收益记录列表")
	public YiwuJson<List<IncomeRecordApiView>> getList(
			@ApiParam(value="id of distributer", required=true)int observerId, 
			@ApiParam(value="id of event type" , 
				allowableValues="[10003  注册,10005 分享推文,10007：购买音之舞产品]", 
				required =true) int eventTypeId,
			@ApiParam(value="id of the relation betweet event subject and observer",
					allowableValues="10015：本人和本人,10016：本人上一级,10017：本人和上两级", 
					required=true)int relationTypeId)
	{
		try {
			List<IncomeRecordApiView> views = new ArrayList<>();
			List<IncomeRecord> records = incomeRecordService.findByProperties(
					new String[]{"benificiary.id", "incomeEvent.type.id", "con_ben_relation.id"}, 
					new Object[]{observerId,eventTypeId,relationTypeId});
			for (IncomeRecord r : records) {
				views.add(new IncomeRecordApiView(r));
			}
			return new YiwuJson<>(views);
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping("/list/shareTweet")
	public YiwuJson<List<IncomeRecordShareTweetApiView>> getShareTweetList(
			@ApiParam(value="id of distributer", required=true)int observerId, 
			@ApiParam(value="id of event type" , 
				allowableValues="[10003  注册,10005 分享推文,10007：购买音之舞产品]", 
				required =true) int eventTypeId,
			@ApiParam(value="id of the relation betweet event subject and observer",
				allowableValues="10015：本人和本人,10016：本人上一级,10017：本人和上两级", 
				required=true)int relationTypeId)
	{
		try{
			List<IncomeRecordShareTweetApiView> views = new ArrayList<>();
			List<IncomeRecord> records = incomeRecordService.findByProperties(
					new String[]{"benificiary.id", "incomeEvent.type.id", "con_ben_relation.id"}, 
					new Object[]{observerId,eventTypeId,relationTypeId});
			for (IncomeRecord r : records) {
				views.add(new IncomeRecordShareTweetApiView(r));
			}
			return new YiwuJson<>(views);
		}catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping("/count")
	public YiwuJson<Integer> getCount(
			@ApiParam(value="id of distributer", required=true)int observerId, 
			@ApiParam(value="id of event type" , 
				allowableValues="[10003  注册,10005 分享推文,10007：购买音之舞产品]", 
				required =true) int eventTypeId,
			@ApiParam(value="id of the relation betweet event subject and observer",
				allowableValues="10015：本人和本人,10016：本人上一级,10017：本人和上两级", 
				required=true)int relationTypeId)
	{
		try {
			int count = incomeRecordService.findCountByProperties(
					new String[]{"benificiary.id", "incomeEvent.type.id", "con_ben_relation.id"}, 
					new Object[]{observerId,eventTypeId,relationTypeId});
			return new YiwuJson<>(new Integer(count));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@GetMapping("/types/count")
	public YiwuJson<Integer> getCountByIncomeTypes(int distributerId, int[] incomeTypeIds){
		try{
			return new YiwuJson<>(new Integer(
					incomeRecordService.findCountByIncomeTypesByBeneficiary(distributerId, incomeTypeIds)));
		}catch (Exception e) {
			logger.warn(e);
			return new YiwuJson<>(e.getMessage());
		}
	}
}
