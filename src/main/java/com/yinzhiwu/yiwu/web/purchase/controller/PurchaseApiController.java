package com.yinzhiwu.yiwu.web.purchase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDistributerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.EmpDistributerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*@Author ping
*@Time  创建时间:2017年7月26日上午11:39:52
*
*/

@RestController
@RequestMapping(value="/api/purchase")
public class PurchaseApiController  extends BaseController{

	@Autowired private OrderYzwService orderSerivice;
	@Autowired private DistributerService distributerService;
	
	@GetMapping(value="/order/list")
	@ApiOperation(value="获取客户的订单列表")
	public YiwuJson<PageBean<OrderDto>> getOrderList(int customerId, boolean isPayed, int pageNo, int pageSize){
		PageBean<OrderDto> orderPage = null;
		if(isPayed)
			orderPage = orderSerivice.findPayedOrderPageByCustomerId(customerId, pageNo, pageSize);
		else
			orderPage = orderSerivice.findUnpayedOrderPageByCustomerId(customerId,pageNo, pageSize);
		return new YiwuJson<>(orderPage);
	}
	
	@PostMapping(value="/login")
	@ApiOperation(value="内部员工使用微信登录登录")
	public YiwuJson<EmpDistributerDto>  employeeLoginByWechat(String wechatNo){
		try {
			EmpDistributerDto view = distributerService.employeeLoginByWechat(wechatNo);
			return new YiwuJson<>(view);
		} catch (YiwuException e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	/**
	 * 可见性说明:ditributer有三个归属属性：
	 * 			1.客户归属：用谁的邀请码注册他的客户归属就是谁
	 * 			2.销售归属：
	 * 			3.门店归属:
	 * 1.教师可以看到客户归属是他的distributers
	 * 2.销售可以看到客户归属和销售归属是他的distributers以及曾经与他成交过订单的distributers
	 * 3.店长满足2，+ 门店归属是他所管辖门店的客户 + 其所管辖的销售的客户
	 * @param distributerId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping(value="/distributer/list")
	@ApiOperation(value="获取客户列表")
	public YiwuJson<PageBean<CustomerDistributerDto>> findDistributersByEmployee(
			@ApiParam(value="内部员工的在分销系统注册后产生的distributer表Id, 非employeeId") 
			int distributerId, int pageNo, int pageSize){
		PageBean<CustomerDistributerDto> views = distributerService.findVisableDistributersByEmployee(distributerId, pageNo, pageSize);
		return new YiwuJson<>(views);
	}
	
	@GetMapping(value="/distributer/list/seach")
	@ApiOperation(value="输入手机号码或者姓名搜索客户")
	public YiwuJson<PageBean<CustomerDistributerDto>> seachVisableDistributersByNameOrPhoneNo(
			@ApiParam(value="用户的姓名或者手机号码， 支持模糊搜索")
			String key, 
			@ApiParam(value="内部员工的在分销系统注册后产生的distributer表Id, 非employeeId") 
			int distributerId, int pageNo, int pageSize){
		PageBean<CustomerDistributerDto> page = distributerService.findVisableDistributersByEmployee(distributerId, pageNo, pageSize);
		//TODO 
		return new YiwuJson<>(page);
	}
	
	
}
