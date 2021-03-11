package com.segmentify.registration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageController {

    private static final int DELAY = 4000;

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(DELAY);
        log.info("slow() is called");
        return "Service is working SLOWLY";
    }
}
