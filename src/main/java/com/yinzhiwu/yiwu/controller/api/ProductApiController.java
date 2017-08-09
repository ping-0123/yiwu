package com.yinzhiwu.yiwu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw;
import com.yinzhiwu.yiwu.service.ProductYzwService;

/**
 * @Author ping
 * @Time 创建时间:2017年7月24日下午3:45:31
 *
 */

@RestController
@RequestMapping("/api/product")
public class ProductApiController extends BaseController {

	@Autowired
	private ProductYzwService productService;

	@GetMapping(value = "/list")
	public ResponseEntity<List<ProductYzw>> doList() {
		List<ProductYzw> products = productService.findAll();
		return new ResponseEntity<List<ProductYzw>>(products, HttpStatus.OK);
	}
}
