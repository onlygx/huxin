package com.elangzhi.ssm.controller.util;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Swagger 配置文件
 */
@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages ={"com.elangzhi.modules"})
public class SwaggerConfig {


    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;


    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .apiVersion("1.0")
                .includePatterns("/app/.*?");
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "互信平台json接口文档",
                "在线即时接口，有疑问及时联系。 ",
                "http://onlygx.eicp.net",
                "only_gx@126.com",
                "My Licence",
                "My License URL");
        return apiInfo;
    }
}