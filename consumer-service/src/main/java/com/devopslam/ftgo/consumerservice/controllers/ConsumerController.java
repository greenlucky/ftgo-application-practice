package com.devopslam.ftgo.consumerservice.controllers;

import com.devopslam.ftgo.consumerservice.domain.Consumer;
import com.devopslam.ftgo.consumerservice.domain.CreateConsumerRequest;
import com.devopslam.ftgo.consumerservice.domain.CreateConsumerResponse;
import com.devopslam.ftgo.consumerservice.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ConsumerController.URL_V1_CONSUMER)
public class ConsumerController {

    public static final String URL_V1_CONSUMER = "/v1/consumers";

    private final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerService consumerService;

    @PostMapping
    public CreateConsumerResponse create(@RequestBody CreateConsumerRequest request) {
        Consumer consumer = consumerService.create(request.getName());
        return new CreateConsumerResponse(consumer.getId());
    }

    @GetMapping("/{consumerId}")
    public Consumer getConsumer(@PathVariable String consumerId) {
        Optional<Consumer> consumer = consumerService.getConsumer(consumerId);
        if (!consumer.isPresent()) throw new RuntimeException("Not found consumer Id: {}" + consumerId);
        return consumer.get();
    }
}
