package com.dipsy.laa.common.util;

import com.dipsy.laa.common.Annotation.Rule;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public final class AnnoManageUtil {

    /**
     *
     * @return
     */
    public static Set<String> getRequestMappingMethod() {
        Reflections reflections = new Reflections("com.dipsy.laa");
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(Rule.class);

        Set<String> rules = new HashSet<>();
        for (Class classes : classesList) {
            //得到该类下面的所有方法
            Method[] methods = classes.getDeclaredMethods();

            for (Method method : methods) {
                rules.add(method.getName());
            }
        }
        return rules;
    }

}