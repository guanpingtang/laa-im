package com.dipsy.laa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置跨域资源共享.
 * 配置信息从 {@link CorsProperties} 获取, 如果已经配置过跨域的拦截器，该类会跳过配置.
 *
 * @author tgp
 */
@Configuration
@Slf4j
public class CorsConfig {

    /**
     * 配置跨域资源共享. 如果已经配置过 资源过滤器，则不进行配置.
     *
     * @param corsProperties 跨域配置属性
     * @return 拦截器
     */
    @Bean
    @ConditionalOnMissingBean(CorsFilter.class)
    public CorsFilter corsFilter(@Autowired CorsProperties corsProperties) {
        if (corsProperties.getMappings() == null || corsProperties.getMappings().size() == 0) {
            log.info("配置跨域拦截器, 共零个配置");
            return new CorsFilter(new UrlBasedCorsConfigurationSource());
        } else {
            log.info("配置跨域拦截器, 共{}个配置.", corsProperties.getMappings().size());
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.setCorsConfigurations(corsProperties.getMappings());
            return new CorsFilter(source);
        }
    }

}