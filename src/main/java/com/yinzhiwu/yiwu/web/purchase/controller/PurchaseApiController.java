package com.yinzhiwu.yiwu.web.purchase.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw.CustomerAgeType;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.ProductCardType;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.DistributerService;
import com.yinzhiwu.yiwu.service.OrderYzwService;
import com.yinzhiwu.yiwu.service.ProductYzwService;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.EmpDistributerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderDto;
import com.yinzhiwu.yiwu.web.purchase.dto.OrderSaveDto;
import com.yinzhiwu.yiwu.web.purchase.dto.ProductDto;
import com.yinzhiwu.yiwu.web.purchase.dto.StoreDto;

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
	@Autowired private DepartmentYzwService departmentService;
	@Autowired private ProductYzwService productService;
//	@Autowired private CustomerYzwService customerService;
	
	@GetMapping(value="/order/list")
	@ApiOperation(value="获取客户的订单列表")
	public YiwuJson<PageBean<OrderDto>> getOrderList(int customerId, boolean isPayed, int pageNo, int pageSize){
		return new YiwuJson<>(orderSerivice.findPageByCustomer(customerId, isPayed, pageNo, pageSize));
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
	public YiwuJson<PageBean<CustomerDto>> findDistributersByEmployee(
			@ApiParam(value="内部员工的在分销系统注册后产生的distributer表Id, 非employeeId") 
			int distributerId, int pageNo, int pageSize){
		PageBean<CustomerDto> views;
		try {
			views = distributerService.findVisableDistributersByEmployee(distributerId, null, pageNo, pageSize);
		} catch (YiwuException e) {
			e.printStackTrace();
			return new YiwuJson<>(e.getMessage());
		}
		return new YiwuJson<>(views);
	}
	
	@GetMapping(value="/distributer/list/seach")
	@ApiOperation(value="输入手机号码或者姓名搜索客户")
	public YiwuJson<PageBean<CustomerDto>> seachVisableDistributersByNameOrPhoneNo(
			@ApiParam(value="用户的姓名或者手机号码， 支持模糊搜索")
			String key, 
			@ApiParam(value="内部员工的在分销系统注册后产生的distributer表Id, 非employeeId") 
			int distributerId, int pageNo, int pageSize){
		PageBean<CustomerDto> page;
		try {
			page = distributerService.findVisableDistributersByEmployee(distributerId, key,pageNo, pageSize);
		} catch (YiwuException e) {
			e.printStackTrace();
			return new YiwuJson<>(e.getMessage());
		}
		return new YiwuJson<>(page);
	}
	
	@GetMapping(value="/store/list/visable")
	@ApiOperation(value="获取操作人可见门店列表")
	public YiwuJson<List<StoreDto>> findVisableStores(
			@ApiParam(value="内部员工Id, 非与之关联的distributerId")int employeeId){
		
		List<StoreDto> stores = departmentService.findVisableStoresByEmployee(employeeId);
		return new YiwuJson<>(stores);
	}
	
	@GetMapping(value="/store/list/all")
	@ApiOperation(value="获取员工所在公司的所有的门店列表")
	public YiwuJson<List<StoreDto>> findAllStores(int employeeId){
		List<StoreDto> stores = departmentService.findAllStores(employeeId);
		return new YiwuJson<>(stores);
	}
	
	@GetMapping(value="/subCourseType/list")
	@ApiOperation(value="根据产品Id获取所有的产品中类类型,\"开放式A\", \"开放式B\", \"封闭式\", \"私教课\", 产品中类可以决定产品大类")
	public YiwuJson<List<SubCourseType>> getPossibleSubCourseTypesOfProduct(int productId){
		ProductYzw product = productService.get(productId);
		if(product == null ) return new YiwuJson<>("无效产品Id" + productId);
		CourseType courseType = null;
		if(product.getContractType() != null)
			courseType = product.getContractType().getContractType();
		if(courseType == null || courseType == CourseType.GRADE_EXAM 
				|| courseType == CourseType.REFERENCE_ORDER)
			return new YiwuJson<>("无须选择中类");
		
		return new YiwuJson<>(SubCourseType.fromCourseType(courseType));
	}
	
	@GetMapping(value="/subCourseType/list/all")
	@ApiOperation(value="获取所有的产品中类")
	public List<SubCourseType>  getAllSubCourseTypes(){
		return Arrays.asList(SubCourseType.values());
	}
	
	
	@GetMapping(value="/productCardType/list")
	@ApiOperation(value="获取所有产品卡类型")
	public List<ProductCardType> getAllProductCardTypes(){
		return Arrays.asList(ProductCardType.values());
	}
	
	@GetMapping(value="/customerAgeType/list")
	@ApiOperation(value="获取客户类型， 少儿？成人")
	public List<CustomerAgeType> getAllCustomerAgeTypes(){
		return Arrays.asList(CustomerAgeType.values());
	}
	
	@GetMapping(value="/product/list")
	@ApiOperation(value="获取产品列表")
	public YiwuJson<List<ProductDto>> findValidProducts(
			int employeeId, ProductCardType cardType, CustomerAgeType ageType){
		
			List<ProductDto> products = productService.findVisableDtoBycardAndAgeType(employeeId,cardType, ageType);
			return new YiwuJson<>(products);
	}
	
	@PostMapping(value="/order")
	@ApiOperation(value="下单， 生成一个新的订单")
	public YiwuJson<OrderSaveDto> save(@Valid OrderSaveDto order, BindingResult result){
		if(result.hasErrors())
			return new YiwuJson<>(super.getErrorsMessage(result));
		try {
			String orderId= orderSerivice.save(order);
			order.setOrderId(orderId);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
		return new YiwuJson<>(order);
	}
	
	@PutMapping(value="/order/{id}")
	@ApiOperation(value="修改订单")
	public YiwuJson<OrderSaveDto> modify(@PathVariable String id, OrderSaveDto order){
		try {
			orderSerivice.modify(id, order);
			return new YiwuJson<>(order);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	@DeleteMapping(value="/order/{id}")
	@ApiOperation(value="删除订单")
	public YiwuJson<Boolean> delete(@PathVariable String id){
		OrderYzw order = orderSerivice.get(id);
		try {
			if(order.isOperatable())
				orderSerivice.delete(order);
			return new YiwuJson<>(Boolean.TRUE);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	
}
