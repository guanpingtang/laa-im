package com.dipsy.laa.main.config;

import com.dipsy.laa.im.ImServerStart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImServerConf {

    @Value("${laa.im.port}")
    private int port;

    @Bean
    public ImServerStart imServerStart() {
        return new ImServerStart(port);
    }
}
