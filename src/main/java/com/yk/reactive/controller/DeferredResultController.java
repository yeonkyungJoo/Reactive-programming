/*
package com.yk.reactive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@RestController
public class DeferredResultController {

    Queue<DeferredResult<String>> results = new ConcurrentLinkedQueue<>();

    @GetMapping("/deferred")
    public DeferredResult<String> deferredResult() {
        log.info("deferred");
        // 응답 대기 - 메모리에 유지
        // worker thread가 따로 생성되지 않는다.
        // ex) 채팅방 구현
        DeferredResult<String> result = new DeferredResult<>();
        results.add(result);
        return result;
    }

    @GetMapping("/deferred/count")
    public String deferredCount() {
        return String.valueOf(results.size());
    }

    @GetMapping("/deferred/event")
    public String deferredEvent(String message) {

        for (DeferredResult<String> result : results) {
            result.setResult(message);
            results.remove(result);
        }
        return "OK";
    }
}
*/
