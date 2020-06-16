package com.qby.config;

import com.qby.interceptor.MyIntercepter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

import java.lang.reflect.Type;

/**
 * springmvc 只扫描Controller，子容器
 * useDefaultFilters = false 禁用默认的过滤规则
 *
 * @author qby
 * @date 2020/6/16 15:57
 */
@ComponentScan(value = {"com.qby"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = {Controller.class})}, useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    /**
     * 是否开启静态资源 静态资源访问
     *
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置视图解析器
     *
     * @param registry
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 默认所有页面都从 /WEB-INF/xxx.jsp
//        registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 拦截器

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepter()).addPathPatterns("/**");
    }
}
