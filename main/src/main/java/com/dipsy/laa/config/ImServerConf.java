package com.dipsy.laa.config;

import com.dipsy.laa.im.transport.ImServerStart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * im 配置类
 * @author tgp
 */
@Configuration
public class ImServerConf {

    @Value("${laa.im.port}")
    private int port;

    @Bean
    public ImServerStart imServerStart() {
        return new ImServerStart(port);
    }

}
