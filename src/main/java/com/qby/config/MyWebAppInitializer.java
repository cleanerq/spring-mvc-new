package com.qby.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web容器启动的时候创建对象，调用方法来初始化容器以及前端控制器
 *
 * @author qby
 * @date 2020/6/16 15:34
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取根容器的配置类
     * spring的配置文件 -> 父容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class};
    }

    /**
     * 获取web容器的配置类
     * Springmvc配置文件 -> 子容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {AppConfig.class};
    }

    /**
     * 获取 DispatcherServlet 的映射信息
     *  / 拦截所有请求（包括静态资源 xx.js,xx.png...）但是不包括*.jsp
     *  /* 拦截所有请求 连 *.jsp 页面都拦截，jsp页面是tomcat的jsp引擎解析的
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
