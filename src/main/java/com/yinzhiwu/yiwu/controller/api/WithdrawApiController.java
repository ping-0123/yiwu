package com.yinzhiwu.yiwu.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.WithdrawEventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @deprecated since v2.1.4
 * @author ping
 * @Date 2017年10月29日 下午9:44:17
 *
 */

@Deprecated
@RestController
@RequestMapping("/api/event/withdraw")
@Api(value = "withdraw")
public class WithdrawApiController extends BaseController {

	@Autowired
	private WithdrawEventService withdrawEventService;

	@PostMapping
	@ApiOperation(value = "提现操作")
	public YiwuJson<Boolean> withdraw(int distributerId, int accountId, float amount) {
		try {
			withdrawEventService.saveWithdraw(distributerId, accountId, amount);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}

		return new YiwuJson<>(new Boolean(true));
	}

}
