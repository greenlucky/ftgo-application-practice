package com.devopslam.ftgo.restaurantservice.events.handler;

import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantOrder;
import com.devopslam.ftgo.restaurantservice.events.RestaurantChangedMessage;
import com.devopslam.ftgo.restaurantservice.events.channels.CustomEventChannels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestaurantSender {

    private final Logger logger = LoggerFactory.getLogger(RestaurantSender.class);

    @Autowired
    private CustomEventChannels sendOut;

    public void sendChangedMessage(RestaurantChangedMessage message) {
        logger.info("Send out restaurant changed message with Id: {}", message.getId());
        sendOut.resChangeOut().send(MessageBuilder.withPayload(message).build());
    }

    public void sendCreateRestaurantOrder(CreateRestaurantOrder restaurantOrder) {
        logger.info("Send out to order service create restaurant order with restaurant order Id: {}", restaurantOrder.getId());
        sendOut.orderCreateOut().send(MessageBuilder.withPayload(restaurantOrder).build());
    }
}
