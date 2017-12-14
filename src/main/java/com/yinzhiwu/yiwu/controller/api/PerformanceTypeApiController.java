
package com.yinzhiwu.yiwu.controller.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;

import io.swagger.annotations.ApiOperation;

/**
 * @author ping
 * @date 2017年12月14日上午11:37:15
 * @since v2.2.0
 *	
 */

@RestController
@RequestMapping("/api/performanceTypes")
public class PerformanceTypeApiController {
	
	@GetMapping
	@ApiOperation("所有业绩类型")
	public List<PerformanceType> findAll(){
		return Arrays.asList(PerformanceType.values());
	}
}
