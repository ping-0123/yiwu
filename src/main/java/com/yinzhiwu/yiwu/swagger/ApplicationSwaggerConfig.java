package com.yinzhiwu.yiwu.swagger;

import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ApplicationSwaggerConfig {

	/**
	 * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
	 * swagger groups i.e same code base multple swagger resource listings.
	 */
	@Bean
	public Docket addUserDocket(){
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		springfox.documentation.service.ApiInfo apiInfo = new ApiInfo(
				"yiwu API", 
				"API Document Management", 
				"v1.0.0",
				"www.baidu.com",
				"wild_ghost@yeah.net", 
				"",
				"");
		docket.apiInfo(apiInfo);
		return docket;
	}
}
