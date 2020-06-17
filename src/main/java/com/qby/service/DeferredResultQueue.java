package com.qby.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author qby
 * @date 2020/6/17 9:48
 */
public class DeferredResultQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedDeque<DeferredResult<Object>>();

    public static void save(DeferredResult<Object> deferredResult) {
        queue.add(deferredResult);

    }

    public static DeferredResult<Object> get() {
        return queue.poll();
    }
}
