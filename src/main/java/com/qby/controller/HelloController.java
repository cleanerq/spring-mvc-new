package com.qby.controller;

import com.qby.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qby
 * @date 2020/6/16 16:08
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        String tomcat = helloService.sayHello("tomcat");
        return tomcat;
    }

    // success
    // /WEB-INF/views/success.jsp
    @RequestMapping("/suc")
    public String success() {
        return "success";
    }
}
