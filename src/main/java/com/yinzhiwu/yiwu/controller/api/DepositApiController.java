package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.DepositService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/event/deposit")
public class DepositApiController extends BaseController {

	@Autowired
	private DepositService depositService;
//	@Autowired private DistributerService distributerService;

	@PostMapping
	@ApiOperation(value="支付定金")
	public YiwuJson<Boolean> payDeposit(int distributerId, float amount, boolean fundsFirst) {
		try {
			if(amount <=0)
				throw new YiwuException("提现金额不能少于零");
			depositService.saveDeposit(distributerId, amount, fundsFirst);
		} catch (Exception e) {
			logger.error(e);
			return new YiwuJson<>(e.getMessage());
		}

		return new YiwuJson<>(new Boolean(true));
	}
}
