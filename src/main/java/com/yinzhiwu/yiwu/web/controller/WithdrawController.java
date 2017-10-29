package com.yinzhiwu.yiwu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.entity.WithdrawBrokerage;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.WithdrawBrokerageException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.view.WithdrawBrokerageVO;
import com.yinzhiwu.yiwu.model.view.WithdrawBrokerageVO.WithdrawBrokerageVOConverter;
import com.yinzhiwu.yiwu.service.WithdrawBrokerageService;
import com.yinzhiwu.yiwu.util.ServletRequestUtils;


/**
* @author 作者 ping
* @Date 创建时间：2017年10月29日 下午11:36:33
*
*/

@Controller
@RequestMapping("/system/withdraws")
public class WithdrawController {
	
	@Autowired private WithdrawBrokerageService withdrawService;
	
	
	@PostMapping(value="/datatable")
	@ResponseBody
	public DataTableBean<?> findDatatable(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		QueryParameter parameter = (QueryParameter) ServletRequestUtils.parseParameter(request, QueryParameter.class);
		DataTableBean<WithdrawBrokerage> table =  withdrawService.findDataTable(parameter);
		List<WithdrawBrokerageVO> vos = new ArrayList<WithdrawBrokerageVO>();
		WithdrawBrokerageVOConverter converter = WithdrawBrokerageVOConverter.INSTANCE;
		for(WithdrawBrokerage withdraw: table.getData()){
			vos.add(converter.fromPO(withdraw));
		}
		
		return new DataTableBean<>(
				table.getDraw(), table.getRecordsTotal(), table.getRecordsFiltered(), vos, table.getError());
	}
	
	@PatchMapping(value="/{id}/payed")
	@ResponseBody
	public YiwuJson<?> payWithdraw(@PathVariable(name="id") Integer id) throws DataNotFoundException, WithdrawBrokerageException{
		
		WithdrawBrokerage withdraw = withdrawService.get(id);
		if(withdraw.getPayed())
			throw new WithdrawBrokerageException("这笔提现已支付给客户， 请不要重复支付");
		withdraw.setPayed(true);
		withdrawService.doPayWithdraw(withdraw);
		
		return YiwuJson.createBySuccess(WithdrawBrokerageVOConverter.INSTANCE.fromPO(withdraw));
	}
}
