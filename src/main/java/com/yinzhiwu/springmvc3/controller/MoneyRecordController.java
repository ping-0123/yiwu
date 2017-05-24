package com.yinzhiwu.springmvc3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.springmvc3.model.PayDepositModel;
import com.yinzhiwu.springmvc3.model.WithDrawModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.MoneyRecordApiView;
import com.yinzhiwu.springmvc3.model.view.OrderMoneyRecordApiView;
import com.yinzhiwu.springmvc3.service.MoneyRecordService;

@RestController
@RequestMapping("/api/moneyRecord")
public class MoneyRecordController {
	
	@Autowired
	private MoneyRecordService mrService;

	/**
	 * 获取资金进出的总记录数
	 * @param distributerId
	 * @return
	 */
	@GetMapping("/getCount")
	public YiwuJson<Integer> getCount(int distributerId){
		return mrService.findCountByDistributerid(distributerId);
	}
	
	
	@GetMapping("/getList")
	public YiwuJson<List<MoneyRecordApiView>> getList(
			@Valid @ModelAttribute  MoneyRecordApiView m,
			BindingResult bindingResult){
		if(bindingResult.hasErrors())
		{
			FieldError fieldError = bindingResult.getFieldError();
			String message = fieldError.getField() + fieldError.getDefaultMessage();
			YiwuJson<List<MoneyRecordApiView>> yiwuJson = new YiwuJson<>();
			yiwuJson.setMsg(message);
			yiwuJson.setResult(false);
			return yiwuJson;
		}
		
		return mrService.findList(m.getBenificiaryId(), m.getCategory());
	}
	
	@PostMapping("/withdraw")
	public YiwuJson<Boolean> withdrawBrockrage(
			@Valid WithDrawModel m, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return new YiwuJson<>(fieldError.getField() + " " + fieldError.getDefaultMessage());
		}
		
		return mrService.saveWithdraw(m);
	}
	
	
	@PostMapping("/payDeposit")
	public YiwuJson<Boolean> payDeposit(@Valid PayDepositModel m, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return new YiwuJson<>(fieldError.getField() + " " + fieldError.getDefaultMessage());
		}
		
		return  mrService.payDeposit(m);
	}
	
	@GetMapping("orderRecords/subordinates")
	public YiwuJson<List<OrderMoneyRecordApiView>> findSubordiatesOrderRecords(int distributerId)
	{
		return mrService.findSubordiatesOrderRecords(distributerId);
	}
	
	@GetMapping("orderRecords/secondaries")
	public YiwuJson<List<OrderMoneyRecordApiView>> findSecondaryOrderRecords(int distributerId)
	{
		return mrService.findSecondaryOrderRecords(distributerId);
	}
}
