package com.tree3.framing.config;

/**
 * @Description
 * @Author: Jinhui
 * @Date 2021/7/7 15:00
 */


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfig {

    /**
     * 自定义配置{@link MybatisSqlSessionFactoryBean}
     * 使用mp-boot-starter 完全可以去掉这些配置，使用yml配置方式, 这里只做示范
     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, GlobalConfiguration globalConfiguration) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setTypeAliasesPackage("com.baomidou.springboot.entity");
//        sqlSessionFactory.setTypeEnumsPackage("com.baomidou.springboot.entity.enums");
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        sqlSessionFactory.setConfiguration(configuration);
//        PaginationInterceptor pagination = new PaginationInterceptor();
//        sqlSessionFactory.setPlugins(new Interceptor[]{
//                pagination,
//                new PerformanceInterceptor()
//        });
//        sqlSessionFactory.setGlobalConfig(globalConfiguration);
//        return sqlSessionFactory.getObject();
//    }
//

    /**
     * 乐观锁插件
     *
     * @return
     */
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
//        return new OptimisticLockerInterceptor();
//    }


    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     * 三种环境:
     * dev：开发环境
     * test：测试环境
     * prod：生产环境
     * #环境设置：dev、test、prod
     * 2
     * spring.profiles.active=dev
     * 可以针对各环境新建不同的配置文件application-dev.properties、application-test.properties、application-prod.properties
     *
     * @return
     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        //ms，超过此处设置的ms则sql不执行
//        performanceInterceptor.setMaxTime(500);
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }
}
