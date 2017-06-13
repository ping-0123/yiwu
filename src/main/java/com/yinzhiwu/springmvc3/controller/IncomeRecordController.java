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

@RestController
@RequestMapping("/api/incomeRecord/")
public class IncomeRecordController extends BaseController{
	
	@Autowired private IncomeRecordService incomeRecordService;
	
	
	@GetMapping("/list")
	public YiwuJson<List<IncomeRecordApiView>> getList(int observerId, int eventTypeId,int relationTypeId){
		
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
	public YiwuJson<List<IncomeRecordShareTweetApiView>> getShareTweetList(int observerId, int eventTypeId,int relationTypeId){
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
	public YiwuJson<Integer> getCount(int observerId, int eventTypeId,int relationTypeId, int incomeTypeId){
		try {
			int count = incomeRecordService.findCountByProperties(
					new String[]{"benificiary.id", "incomeEvent.type.id", "con_ben_relation.id", "incomeType.id"}, 
					new Object[]{observerId,eventTypeId,relationTypeId,incomeTypeId});
			return new YiwuJson<>(new Integer(count));
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
}
