package org.whu.cs.config;

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
 * Swagger文档配置
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * Create rest api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.whu.cs"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("一时打卡 | 接口说明文档")
                .description("基于 Swagger 生成")
                .termsOfServiceUrl("http://group.hellogod.cn/")
                .contact("lc-group-dev")
                .version("1.0")
                .build();
    }

}