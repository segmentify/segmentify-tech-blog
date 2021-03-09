package com.emir.ordermanagement.controller;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.function.Supplier;

@RestController
@Slf4j
public class BulkheadController {

    private final RestTemplate restTemplate;
    private final Bulkhead bulkhead;

    public BulkheadController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.bulkhead = createBulkHead(10);
    }

    @GetMapping("/bulkhead")
    public String bulkhead() {
        // decorate call with bulkhead
        Supplier<String> register = Bulkhead.decorateSupplier(bulkhead, this::registerPayment);
        // execute the call
        Try<String> result = Try.ofSupplier(register);
        if (result.isSuccess()) {
            return result.get();
        } else {
            return "default-response";
        }
    }

    private Bulkhead createBulkHead(int maxConcurrent) {
        BulkheadConfig bulkheadConfig = BulkheadConfig.custom()
                .maxConcurrentCalls(maxConcurrent)
                .maxWaitDuration(Duration.ofMillis(100))
                .build();
        Bulkhead bulkhead = Bulkhead.of("registrationService", bulkheadConfig);
        bulkhead.getEventPublisher()
                .onCallPermitted(event -> log.info("Call permitted"))
                .onCallRejected(event -> log.info("Call rejected"));
        return bulkhead;
    }

    private String registerPayment() {
        return "The message was" + restTemplate.getForObject("/slow", String.class);
    }

}
