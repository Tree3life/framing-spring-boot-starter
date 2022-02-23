package com.tree3.framing.config;

import com.tree3.framing.handler.bale.ResponseResultInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description Web 配置中心
 * @Author: Jinhui
 * @Date 2021/11/12 17:19
 */
public class WebMvcConfig implements WebMvcConfigurer {
    public static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    /**
     * 注册@ResponseResult拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug(" 注册@ResponseResult拦截器");
        //自定义的请求拦截器：检查请求方法上是否被@ResponseResult标识
        ResponseResultInterceptor responseResultInterceptor = new ResponseResultInterceptor();
        //统一结果封装
        registry.addInterceptor(responseResultInterceptor);
    }
}
