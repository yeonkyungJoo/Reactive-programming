package com.yk.reactive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.Executors;

@Slf4j
@RestController
public class EmitterController {

    // 데이터를 한번에 보내지 않고,
    // 쪼개서 보내는 방식
    @GetMapping("/emitter")
    public ResponseBodyEmitter emitter() {

        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        Executors.newSingleThreadExecutor().submit(() -> {

            try {
                for (int i=1; i<=50; i++) {
                    emitter.send("<p>Stream" + i + "</p>");
                    Thread.sleep(100);
                }
            } catch (Exception e) {

            }
        });
        return emitter;

    }
}
