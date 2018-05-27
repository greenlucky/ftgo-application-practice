package com.devopslam.ftgo.consumerservice.service;

import com.devopslam.common.domain.PersonName;
import com.devopslam.ftgo.consumerservice.domain.Consumer;
import com.devopslam.ftgo.consumerservice.repository.ConsumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private ConsumerRepository consumerRepository;

    @Transactional
    public Consumer create(PersonName name) {
        String id = UUID.randomUUID().toString();
        logger.info("Create new consumer with Id: {}", id);
        Consumer consumer = new Consumer(id, name);
        consumerRepository.save(consumer);
        return consumer;
    }

    public Optional<Consumer> getConsumer(String consumerId) {
        return Optional.ofNullable(consumerRepository.findOne(consumerId));
    }
}
