package com.lai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by lailai on 2017/9/15.
 * swagger配置类
 * 与Spring MVC程序配合组织出强大RESTful API文档
 * 让维护文档和修改代码整合为一体
 * 让我们在修改代码逻辑的同时方便的修改文档说明
 * 另外Swagger2也提供了强大的页面测试功能来调试每个RESTful API
 */
@Configuration //让spring来加载该类配置
@EnableSwagger2 //启动swagger
public class Swagger {
    @Bean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()       //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                .apis(RequestHandlerSelectors.basePackage("com.lai.web")) //扫描指定包路径下的所以Controller定义的API
                .paths(PathSelectors.any())
                .build();
    }

    //创建该api的基本信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring boot 中使用swagger2构建restfull APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("不懂IT的小白")
                .version("1.0")
                .build();
    }
}
