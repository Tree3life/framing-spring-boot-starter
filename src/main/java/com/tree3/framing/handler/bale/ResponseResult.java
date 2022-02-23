package com.tree3.framing.handler.bale;

import java.lang.annotation.*;

/**
 * @Description 标记该方法的返回结果 需要被 封装
 * 判断是否需要包装返回值
 * <p>
 * 解析该注解的步骤
 * 1：拦截请求，判断 该请求是否被本注解进行了标记；
 * 2：实现接口ResponseBodyAdvice和@ControllerAdvice，在该实现类中对该方法的返回值进行包装。
 * 3.@ResponseBody会使该注解失效
 * @Author: Jinhui
 * @Date 2021/11/9 16:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {

}
