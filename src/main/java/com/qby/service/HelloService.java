package com.qby.service;

import org.springframework.stereotype.Service;

/**
 * @author qby
 * @date 2020/6/16 16:09
 */
@Service
public class HelloService {

    public String sayHello(String name) {
        return "hello " + name;
    }
}
