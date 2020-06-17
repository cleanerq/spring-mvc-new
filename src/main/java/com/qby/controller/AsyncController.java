package com.qby.controller;


import com.qby.service.DeferredResultQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author qby
 * @date 2020/6/16 23:50
 */
@Controller
public class AsyncController {
    Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 1、控制器返回 Callable
     * 2、Spring MVC 会异步处理，将 Callable 提交到 TaskExecutor 使用一个隔离的线程进行执行
     * 3、DispatcherServlet 和所有的 Filter退出web容器的线程，但是response 还保持打开状态
     * 4、Callable 返回结果，Spring MVC 将请求重新派发给容器 恢复之前的处理
     * 5、根据 Callable返回结果 Spring MVC 继续进行视图渲染流程等（从收请求-视图渲染）
     * <p>
     * preHandle..../js/async01
     * 2020-06-17 00:00:32.817 主线程开始...Thread[http-nio-8080-exec-5,5,main] =====> 1592323232817
     * 2020-06-17 00:00:32.822 主线程结束...Thread[http-nio-8080-exec-5,5,main] =====> 1592323232822
     * =================DispatcherServlet及所有的Filter都退出线程====================================
     * <p>
     * <p>
     * =================等待 Callable 执行===============================================
     * 2020-06-17 00:00:32.834 子线程开始...Thread[MvcAsync1,5,main] =====> 1592323232834
     * 2020-06-17 00:00:35.835 子线程结束...Thread[MvcAsync1,5,main] =====> 1592323235835
     * =================Callable 执行完成===============================================
     * <p>
     * =====================================
     * preHandle..../js/async01
     * postHandle....Callable的之前的返回值就是目标方法的返回值
     * afterCompletion....
     * <p>
     * 异步的拦截器：
     * 1）、原生API的AsyncListener
     * 2）、Spring MVC 实现AsyncHandlerInterceptor
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {

        logger.info("主线程开始...{} =====> {}", Thread.currentThread(), System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {

            public String call() throws Exception {
                logger.info("子线程开始...{} =====> {}", Thread.currentThread(), System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
                logger.info("子线程结束...{} =====> {}", Thread.currentThread(), System.currentTimeMillis());
                return "Callable<String> async01()";
            }
        };

        logger.info("主线程结束...{} =====> {}", Thread.currentThread(), System.currentTimeMillis());
        return callable;
    }


    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> deferredResult =
                new DeferredResult(3000L, "create fail ");

        DeferredResultQueue.save(deferredResult);

        return deferredResult;
    }


    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        String s = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(s);

        return "success----->" + s;
    }
}
