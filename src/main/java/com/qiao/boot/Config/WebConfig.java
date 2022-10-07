package com.qiao.boot.Config;

import com.qiao.boot.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 乔世伟
 * @Project_Nmae: spring-boot-web1
 * @Description:
 * @date 2022/10/5 12:30
 */
//想查看其原理点击注解进入
//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**");


    }
}
