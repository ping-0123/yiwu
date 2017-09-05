package com.yinzhiwu.yiwu.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.ReturnedJson;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.YiwuJson.ReturnCode;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.LessonApiView;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;
import com.yinzhiwu.yiwu.service.CheckInsYzwService;
import com.yinzhiwu.yiwu.service.OrderService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApiController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderYzwService orderYzwService;
	@Autowired
	private DistributerDao distributerDao;
	@Autowired
	private CheckInsYzwService checkInsService;

	@RequestMapping(value = "/getDailyOrders", method = { RequestMethod.GET, RequestMethod.POST })
	public ReturnedJson getDailyOrdersByStore(@RequestParam int storeId, @RequestParam Date payedDate,
			@RequestParam int productTypeId) {
		if (productTypeId == 0) {
			return new ReturnedJson(orderService.getDailyOrderByStore(storeId, payedDate));
		} else {
			return new ReturnedJson(orderService.getDailyOrderByStore(storeId, payedDate, productTypeId));
		}
	}

	@Deprecated
	@GetMapping(value = "/list")
	public YiwuJson<List<OrderAbbrApiView>> findByDistributerId(int distributerId) {
		return orderYzwService.findByDistributerId(distributerId);
	}

	@GetMapping(value="")
	@ApiOperation(value="查询客户的订单列表")
	public YiwuJson<PageBean<OrderApiView>>  findPageOfOrderApiViewByDistributerId(
			@RequestParam(value="distributerId", required =true) Integer distributerId,
			@RequestParam(value="pageNo", defaultValue="1", required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue="10", required=false) int pageSize){
		
		Distributer distributer = distributerDao.get(distributerId);
		if(distributer == null )
			return YiwuJson.createByErrorMessage("不存在Id为:" + distributerId + " 的客户");
		return orderYzwService.findPageOfOrderApiViewByDistributer(distributer,  pageNo,  pageSize);
	}
	
	@GetMapping(value = "/count")
	public YiwuJson<Long> findCount(int customerId) {
		try {
			Long count = orderYzwService.findCountByProperty("customer.id", customerId);
			return new YiwuJson<>(count);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	@GetMapping(value = "/{id}")
	public YiwuJson<OrderApiView> findById(@PathVariable String id) {
		return orderYzwService.findById(id);
	}

	@Deprecated
	@PutMapping("/{id}")
	public YiwuJson<Boolean> modify(OrderYzw order, @PathVariable String id) {
		try {
			orderYzwService.modify(id, order);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
		if (order.geteContractStatus())
			return new YiwuJson<>("成功确认订单", new Boolean(true));
		return new YiwuJson<>(new Boolean(true));
	}
	
	@PutMapping("/{orderId}/contract/status")
	@ApiOperation(value="确认合同")
	public YiwuJson<Boolean> modifyContractStatus(
			@ApiParam(name="orderId", required=true) @PathVariable(name="orderId",  required=true) 
			String id, 
			@ApiParam(name="status", value="VERIFIED,CHECKED,LEFT,RETURNED_PREMIUM,FORBIDDEN,EXPIRED", required=true)
			ContractStatus status) 
	{
		OrderYzw order = orderYzwService.get(id);
		if(order == null)
			return YiwuJson.createByErrorCodeMessage(ReturnCode.ILLEGAL_ARGUMENT.getCode(), "无效的订单Id" + id);
		Contract contract = order.getContract();
		if(contract == null)
			return YiwuJson.createByErrorMessage("order:" + id + "不存在会籍合约");
		
		switch (status) {
		case VERIFIED:
			if(!ContractStatus.UN_VERIFIED.equals(contract.getStatus()))
				//订单只有在未确认的状态下才能确认
				return YiwuJson.createByErrorMessage("当前合约处于"+ contract.getStatus() + " 不能转为状态" + ContractStatus.VERIFIED );
			
			//当折扣小于1， 订单需要审核
			if(order.getDiscount()<1)
				contract.setStatus(ContractStatus.UN_CHECKED);
			else
				contract.setStatus(ContractStatus.CHECKED);
			orderYzwService.update(order);
		case CHECKED:
		case LEFT:
		case RETURNED_PREMIUM:
		case FORBIDDEN:
		case EXPIRED:
			//TODO
			break;
		default:
			return YiwuJson.createByErrorCodeMessage(ReturnCode.ILLEGAL_ARGUMENT.getCode(), status.toString() + "is not a legal argument") ;
		}
		return YiwuJson.createBySuccess(Boolean.TRUE);
	}
	
	
	@GetMapping(value="/contract/type/private")
	@ApiOperation(value="获取客户所有的私教课会籍合约")
	public YiwuJson<List<PrivateContractApiView>> getPrivateContracts(Integer customerId){
		
		return orderYzwService.getPrivateContractsByCustomer(customerId);
	}
	
	@GetMapping(value="/contracts/{contractNo}/checkedInLessons")
	@ApiOperation(value="查询{contractNo}下已签到(即已上课)的课程")
	public YiwuJson<PageBean<LessonApiView>> findCheckedLessonsByContractNo(
			@PathVariable(value="contractNo") String contractNo,
			@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo,
			@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize)
	{
		if(!StringUtils.hasLength(contractNo))
			return YiwuJson.createByErrorMessage("请传入正确的 contractNo");
		return checkInsService.findPageOfCheckedInLessonApiViewsByContractNo(contractNo,pageNo, pageSize );
	}
	
}
