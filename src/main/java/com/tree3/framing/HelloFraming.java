package com.tree3.framing;

/**
 * @Description Framing自述文件
 * @Author: Jinhui
 * @Date 2021/12/31 14:50
 */
public class HelloFraming {
    public static void start() {
        datasourceConfig();
        mybatisPlus();
        logSetting();
    }

    public static void datasourceConfig() {
        System.out.println("#数据库配置");
        System.out.println("spring:\n" +
                "  datasource:\n" +
                "    username: root\n" +
                "    password: root\n" +
                "    url: jdbc:mysql://localhost:3306/数据库名?useSSL=false&serverTimezone=Asia/Shanghai\n" +
                "    driver-class-name: com.mysql.cj.jdbc.Driver");
        System.out.println();
    }

    public static void mybatisPlus() {
        System.out.println("#mybatis-plus配置          @MapperScan(\"mapper包路径\")");
        System.out.println("mybatis-plus:\n" +
                "  mapper-locations: classpath:mapper/*Mapper.xml\n" +
                "  type-aliases-package: 实体类全限定包名");
        System.out.println();
    }

    public static void logSetting() {
        System.out.println();
        System.out.println("framing:\n" +
                " property:\n" +
                "  showCase: true");
        System.out.println();
    }
/**
 * 作用：
 - 统一封装返回结果
 - 全局异常处理
 - 跨域配置
 - mybatis-plus依赖
 - hutool工具类

 <dependency>
 <groupId>com.tree3</groupId>
 <artifactId>framing-spring-boot-starter</artifactId>
 <version>1.0-RELEASE</version>
 </dependency>


 spring:
 datasource:
 username: root
 password: zbhqm139
 url: jdbc:mysql://localhost:3306/数据库名?useSSL=false&serverTimezone=Asia/Shanghai
 driver-class-name: com.mysql.cj.jdbc.Driver


 mybatis-plus:
 mapper-locations: classpath:mapper/*Mapper.xml
 type-aliases-package: 实体类全限定包名

 framing:
 property:
 showCase: true
 */
}
