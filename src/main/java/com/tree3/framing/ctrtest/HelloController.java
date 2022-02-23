package com.tree3.framing.ctrtest;

import com.tree3.framing.handler.bale.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Jinhui
 * @Date 2021/12/30 9:52
 */
@RestController
public class HelloController {
    public static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    @ResponseResult
    public String hello() {
        System.out.println();
//        System.out.println(1/0);
        logger.info("hello framing!");
        return "hello framing";
    }
}
