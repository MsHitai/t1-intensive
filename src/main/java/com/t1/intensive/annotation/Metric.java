package com.t1.intensive.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Metric {
    /**
     * Specify the amount of milliseconds a method is allowed to execute
     *
     * @return {@link Long}
     */
    long limit() default 1L;
}
