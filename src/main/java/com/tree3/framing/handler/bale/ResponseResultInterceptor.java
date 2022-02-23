package com.tree3.framing.handler.bale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description 拦截器
 * 拦截请求并检查该请求对应的方法上是否带有@ResponseResult标识
 * 有：在请求中添加 键值对；--> request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class))
 * 无：不做处理；
 * @Author: Jinhui
 * @Date 2021/11/9 17:08
 */
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    //用于标记 方法 需要被包装
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    private Logger logger = LoggerFactory.getLogger(ResponseResultInterceptor.class);

    /**
     * 拦截请求，判断该请求对应的方法是否被 @ResponseResult标识，
     * 在请求中添加 请求结果需要被包装的标志
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Step 1: 判断该方法是否有@RequestMapping注解：通过判断该方法是否是HandlerMethod的一个实例
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Class<?> clazz = handlerMethod.getBeanType();
        final Method method = handlerMethod.getMethod();

        //判断是否被@ResponseBody标注，若被标注则直接放行
        if (clazz.isAnnotationPresent(ResponseBody.class) || method.isAnnotationPresent(ResponseBody.class)) {
            return true;
        }

        //对 被@ResponseResult标识的方法，进行标注：该方法的返回值需要  在 @{ResponseBodyAdvice} 接口中  重新封装;
        if (clazz.isAnnotationPresent(ResponseResult.class)) {//Step 2: 判断类对象上 是否被@ResponseResult标识
            logger.debug("标记请求---class，添加打包标记：" + clazz.getSimpleName() + ":::" + clazz.getAnnotation(ResponseResult.class));
            request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
        } else if (method.isAnnotationPresent(ResponseResult.class)) {//Step 3: 判断方法上 是否被@ResponseResult标识
            logger.debug("标记请求---method，添加打包标记：" + method.getName() + ":::" + method.getAnnotation(ResponseResult.class));
            request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
        }
        return true;
    }
}
