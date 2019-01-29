package com.dipsy.laa.common.Annotation;

import java.lang.annotation.*;

/**
 * 规则
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Rule {

    String name() default "";

}
