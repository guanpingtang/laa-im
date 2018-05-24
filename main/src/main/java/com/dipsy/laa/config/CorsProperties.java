package com.dipsy.laa.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * 跨域共享资源属性配置.
 *
 * @author tgp
 */
@Configuration
@ConfigurationProperties(prefix = "spring.mvc.cors")
public class CorsProperties {

    private Map<String, CorsConfiguration> mappings = new HashMap<>();

    public CorsProperties() {
    }

    public Map<String, CorsConfiguration> getMappings() {
        return mappings;
    }

    public void setMappings(Map<String, CorsConfiguration> mappings) {
        this.mappings = mappings;
    }
}