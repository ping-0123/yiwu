package com.yinzhiwu.yiwu.web.operating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.service.ElectricContractTypeService;

/**
* @author 作者 ping
* @Date 创建时间：2017年12月3日 下午7:46:06
*
*/

@Controller
@RequestMapping("/system/eContractTypes")
public class ElectricContractTypeController extends BaseController {
	
	@Autowired private ElectricContractTypeService ectService;
	
	@GetMapping("/{id}/contractType")
	@ResponseBody
	public YiwuJson<?> getCourseType(@PathVariable(name="id") Integer id) throws DataNotFoundException{
		return YiwuJson.createBySuccess(ectService.get(id).getContractType());
	}
}
