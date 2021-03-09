package com.emir.registrationservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@Slf4j
public class MessageController {

    private int count = 0;
    private static final int DELAY = 500;

    @GetMapping("/")
    public String okay() {
        return "Service is working fine";
    }

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(DELAY);
        return "Service is working SLOWLY";
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("Service is not available");
    }

    @GetMapping("/erratic")
    public String erratic() throws InterruptedException {
        log.info(Integer.toString(count++));
        if (ThreadLocalRandom.current().nextInt(0, 5) == 0) {
            log.error("erratic");
            throw new RuntimeException("I'm erratic");
        }
        log.info("ok");
        return "Service is working fine for now";
    }
}
