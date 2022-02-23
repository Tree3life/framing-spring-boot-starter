package com.tree3.framing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description 本框架中的自定义配置
 * @Author: Jinhui
 * @Date 2021/12/30 14:12
 */
@ConfigurationProperties(prefix = "framing.property")
public class FramingProperties {
    /**
     * 开启全局异常处理捕获异常后打印堆栈信息
     */
    private boolean showCase;

    public FramingProperties() {
    }

    public boolean isShowCase() {
        return showCase;
    }

    public void setShowCase(boolean showCase) {
        this.showCase = showCase;
    }
}
