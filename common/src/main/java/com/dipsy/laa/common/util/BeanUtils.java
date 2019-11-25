package com.dipsy.laa.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class BeanUtils implements ImportBeanDefinitionRegistrar {

    private static DefaultListableBeanFactory beanFactory;

    public static <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }

    public static <T> T getBean(String name) {
        return (T) beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.info("Register BeanUtils..");
        beanFactory = (DefaultListableBeanFactory) registry;
    }
}