package com.devopslam.ftgo.orderservice.events.handlers;

import com.devopslam.ftgo.orderservice.events.channels.CustomEventChannels;
import com.devopslam.ftgo.orderservice.service.RestaurantService;
import com.devopslam.ftgo.restaurantservice.events.RestaurantChangedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantChangeEventHandler {

    private final Logger logger = LoggerFactory.getLogger(RestaurantChangeEventHandler.class);

    @Autowired
    private RestaurantService restaurantService;

    @StreamListener(CustomEventChannels.RES_CHANGE_CHANNEL)
    public void resSink(RestaurantChangedMessage message) {
        logger.info("Received a message of type: {} with", message.getAction());
        switch (message.getAction()) {
            case DELETE:
            case UPDATE:
                restaurantService.delete(message.getId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the Restaurant service");
        }
    }
}
