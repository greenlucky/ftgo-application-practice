package com.devopslam.ftgo.consumerservice.events.handlers;

import com.devopslam.common.events.IMessageEvent;
import com.devopslam.common.events.MessageEvent;
import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;
import com.devopslam.ftgo.consumerservice.domain.Consumer;
import com.devopslam.ftgo.consumerservice.events.channels.CustomEventChannels;
import com.devopslam.ftgo.consumerservice.service.ConsumerService;
import com.devopslam.ftgo.orderservice.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderCreateEventHandler {

    private final Logger logger = LoggerFactory.getLogger(OrderCreateEventHandler.class);

    @Autowired
    private ConsumerService consumerService;

    @StreamListener(CustomEventChannels.ORDER_CREATE_IN_CHANNEL)
    @SendTo(CustomEventChannels.ORDER_CREATE_OUT_CHANNEL)
    public IMessageEvent orderCreateValidateConsumerSink(IMessageEvent msg) {
        String orderId = msg.getOrderId();
        logger.info("Receives event from order service with order Id: {}", orderId);
        MessageStatusEvent statusRely = MessageStatusEvent.FAILURE;
        try {
            if (msg.getDataEvent() == null || !(msg.getDataEvent() instanceof OrderCreateEvent)) return null;
            OrderCreateEvent event = (OrderCreateEvent) msg.getDataEvent();


            Optional<Consumer> consumer = consumerService.getConsumer(event.getConsumerId());
            if (!consumer.isPresent()) throw new RuntimeException("Not found consumer Id: " + event.getConsumerId());

            if (consumer.get().getMoney().getAmount().compareTo(event.getTotal().getAmount()) >= 0)
                statusRely = MessageStatusEvent.SUCCESS;
            return new MessageEvent(orderId, ServiceEvent.CONSUMER_SERIVCE, statusRely);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new MessageEvent(orderId, ServiceEvent.CONSUMER_SERIVCE, statusRely);
        }
    }

}
