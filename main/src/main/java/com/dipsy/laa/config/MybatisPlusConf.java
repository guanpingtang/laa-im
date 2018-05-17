package com.dipsy.laa.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis 配置
 * @author tgp
 */
@Configuration
@MapperScan(basePackages = {"com.dipsy.laa.*.mapper*"})
public class MybatisPlusConf {

    // mapper 文件的路径，扫描所有 jar 包 下面的 mapper 路径
    private static final String MAPPER_FILE_PATH = "classpath*:/mapper/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_FILE_PATH));
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        // 使用的是mybatisPlus，所以使用mybatisPlus configuration
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setCacheEnabled(false);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);


        // mybatisPlus globalConfig
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setIdType(IdType.INPUT.getKey());
        sqlSessionFactoryBean.setGlobalConfig(globalConfiguration);


        sqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor() //添加分页功能
        });

        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);//开启分页支持
        return paginationInterceptor;
    }

    /**
     * mybatis-plus SQL执行效率插件,生产环境可以关闭
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

}