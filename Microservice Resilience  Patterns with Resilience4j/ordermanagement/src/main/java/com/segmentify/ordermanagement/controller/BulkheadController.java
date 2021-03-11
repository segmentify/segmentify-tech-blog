package com.segmentify.ordermanagement.controller;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@RestController
@Slf4j
public class BulkheadController {

    private final RestTemplate restTemplate;
    private final Bulkhead bulkhead;

    public BulkheadController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.bulkhead = createBulkHead();
    }

    @GetMapping("/bulkhead")
    public void bulkhead() {
        // decorate call with bulkhead
        Supplier<String> register = Bulkhead.decorateSupplier(bulkhead, this::registerPayment);
        // execute the call
        for (int i = 0; i < 10; i++) {
            log.info("loop is called i:" + i);
            CompletableFuture
                    .supplyAsync(register)
                    .thenAccept(log::info);
        }
    }

    private Bulkhead createBulkHead() {
        BulkheadConfig bulkheadConfig = BulkheadConfig.custom()
                .maxConcurrentCalls(3)
                .maxWaitDuration(Duration.ofMillis(100))
                .build();
        Bulkhead bulkhead = Bulkhead.of("registrationService", bulkheadConfig);
        bulkhead.getEventPublisher()
                .onCallPermitted(event -> log.info("Call permitted"))
                .onCallRejected(event -> log.info("Call rejected"));
        return bulkhead;
    }

    private String registerPayment() {
        return "Payment is registered " + restTemplate.getForObject("/slow", String.class);
    }

}
