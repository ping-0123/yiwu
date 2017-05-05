package com.yinzhiwu.springmvc3.config;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.EnableWebMvc;  
  
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;  
import com.mangofactory.swagger.models.dto.ApiInfo;  
import com.mangofactory.swagger.plugin.EnableSwagger;  
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;  
  
@Configuration  
@EnableSwagger  
@EnableWebMvc  
public class SwaggerConfig {  
    private SpringSwaggerConfig springSwaggerConfig;  
  
    /** 
     * Required to autowire SpringSwaggerConfig 
     */  
    @Autowired  
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {  
        this.springSwaggerConfig = springSwaggerConfig;  
    }  
  
    /** 
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc 
     * framework - allowing for multiple swagger groups i.e. same code base 
     * multiple swagger resource listings. 
     */  
    @Bean  
    public SwaggerSpringMvcPlugin customImplementation() {  
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
        								.apiInfo(apiInfo())
        								.includePatterns(".*")
        								.apiVersion("v1");
    }  
  
    private ApiInfo apiInfo() {  
        ApiInfo apiInfo = new ApiInfo(  
                "ums接口文档",  
                "这里是所有的ums接口，里边含有说明，请自行测试",  
                "http://192.168.0.115:8080/yiwu",  
                "wild_ghost@yeah.net",  
                "My Apps API Licence Type",  
                "My Apps API License URL");  
        return apiInfo;  
    }  
}  