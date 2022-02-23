package com.tree3.framing.config;

import com.tree3.framing.handler.bale.ResponseResultHandler;
import com.tree3.framing.handler.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description Framing自动装配类
 * @Author: Jinhui
 * @Date 2021/12/30 14:21
 */
@Configuration
@EnableConfigurationProperties(FramingProperties.class)
public class FramingConfiguration {
    public static Logger logger = LoggerFactory.getLogger(FramingConfiguration.class);
    @Autowired
    private final FramingProperties properties;

    public FramingConfiguration(FramingProperties properties) {
        logger.info("自动装配Framing...");
        logger.info("@ResponseResult------包装响应结果");
        logger.info("@解决跨域------跨域");
        logger.info("全局异常处理&自定义业务异常BizException");
        this.properties = properties;
    }

    /**
     * 配置mvc{添加@ResponseResult拦截器}
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(WebMvcConfig.class)
    public WebMvcConfig webMvcConfig() {
        return new WebMvcConfig();
    }

    /**
     * 注册封装结果的切面
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ResponseResultHandler.class)
    public ResponseResultHandler responseResultHandler() {
        logger.debug(" 注册用于封装结果的切面 ");
        return new ResponseResultHandler();
    }

    /**
     * 配置全局异常处理器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        logger.debug(" 配置全局异常处理器 ");
        return new GlobalExceptionHandler(properties);
    }

    /**
     * 解决跨域问题
     * springboot版本        <version>2.1.5.RELEASE</version>
     */
    @Bean
    @ConditionalOnMissingBean(FilterRegistrationBean.class)
    public FilterRegistrationBean corsFilter() {
        logger.debug(" 配置跨域过滤器 ");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config); // CORS 配置对所有接口都有效
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
