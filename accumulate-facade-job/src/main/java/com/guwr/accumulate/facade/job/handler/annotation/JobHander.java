package com.guwr.accumulate.facade.job.handler.annotation;

import java.lang.annotation.*;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.handler.annotation.JobHander
 * Date 2016/9/27
 * Time 11:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JobHander {
    String value() default "";
}
