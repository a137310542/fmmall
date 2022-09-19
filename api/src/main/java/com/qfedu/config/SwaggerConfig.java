package com.qfedu.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{
    /*swagger会帮助我们生成接口文档*/
   //1。配置文档信息
    //2.配置生成的接口
    //服装接口文档信息
    public Docket getDocket(){

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("《蜂蜜商城》后端接口说明").description("说明后端规范").version("V2.0.1")
        .contact(new Contact("鹏哥","www.hao123.com","137310542@qq.com"));
        Docket docket=new Docket(DocumentationType.SWAGGER_2);//指定文档风格
        ApiInfo apiInfo=apiInfoBuilder.build();
        //如何获得接口
        //new接口，需要在构造器后实现所有抽象方法
        //new子类/实现类
        //工厂模式
        docket.apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage("com.qfedu.controler"))
        .paths(PathSelectors.any())
        .build();
        ;//指定生成文档信息
        return  docket;
    }
}