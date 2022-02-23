package com.tree3.framing.handler.bale;

import com.tree3.framing.handler.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 统一处理返回结果的切面（Aop）
 * 处理响应报文，对被@ResponseHandler标注的方法返回结果进行 再封装
 * @Author: Jinhui
 * @Date 2021/11/12 14:51
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";
    private Logger logger = LoggerFactory.getLogger(ResponseResultHandler.class);

    /**
     * 对beforeBodyWrite()的执行者进行筛选
     *
     * @param returnType
     * @param converterType
     * @return true：执行beforeBodyWrite()；false：不执行beforeBodyWrite()
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = requestAttributes.getRequest();
        //判断请求是否有 再包装 标记
        ResponseResult responseHandler = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);

        return responseHandler == null ? false : true;
    }

    /**
     * 对返回结果进行再封装（统一封装） 为RespResult
     * RespResult --> 直接传递
     * 其它-->RespResult
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        logger.debug("封装返回结果");

        if (body instanceof RespResult.ErrorResult) {
            return body;
        }

        if (body instanceof RespResult) {
            return body;
        }

        /**
         * SpringMVC中后期可能会对String类型的返回值做特殊处理，因此不对String类型的返回值做任何处理
         * 如果有向前台返回一个String类型的需求：可以通过直接该控制器的返回值类型设置为RespResult，直接调用RespResult.success(body)
         */
        if (body instanceof String) {
            return body;
        }
        if (body instanceof ModelAndView) {
            throw new BizException(ResponseCode.MEANINGLESS_USE, "在返回值类型为ModelAndView的方法上使用@ResponseResult是无意义的！");
        }

        //正常数据封装
        return RespResult.success(body);
    }
}
