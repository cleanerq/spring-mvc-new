package com.qby.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * spring的容器不扫描Controller 父容器
 * @author qby
 * @date 2020/6/16 15:57
 */
@ComponentScan(value = {"com.qby"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
public class RootConfig {
}
