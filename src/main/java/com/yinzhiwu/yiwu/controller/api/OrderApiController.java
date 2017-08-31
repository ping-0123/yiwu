package com.yinzhiwu.yiwu.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.dao.DistributerDao;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.ReturnedJson;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderAbbrApiView;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;
import com.yinzhiwu.yiwu.service.OrderService;
import com.yinzhiwu.yiwu.service.OrderYzwService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApiController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderYzwService orderYzwService;
	@Autowired
	private DistributerDao distributerDao;

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
			Integer distributerId,
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

	@PutMapping("/{id}")
	public YiwuJson<Boolean> modify(OrderYzw order, @PathVariable String id) {
		// if(order.geteContractStatus()){
		// Contract contract = new Contract();
		// contract.setStatus("已确认");
		// order.setContract(contract);
		// }
		try {
			orderYzwService.modify(id, order);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		}
		if (order.geteContractStatus())
			return new YiwuJson<>("成功确认订单", new Boolean(true));
		return new YiwuJson<>(new Boolean(true));
	}

	@GetMapping(value="/contract/type/private")
	@ApiOperation(value="获取客户所有的私教课会籍合约")
	public YiwuJson<List<PrivateContractApiView>> getPrivateContracts(Integer customerId){
		
		return orderYzwService.getPrivateContractsByCustomer(customerId);
	}
}
