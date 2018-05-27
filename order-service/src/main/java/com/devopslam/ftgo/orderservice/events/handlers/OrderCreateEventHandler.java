package com.devopslam.ftgo.orderservice.events.handlers;

import com.devopslam.common.events.IMessageEvent;
import com.devopslam.common.events.MessageEvent;
import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;
import com.devopslam.ftgo.orderservice.domain.RestaurantOrder;
import com.devopslam.ftgo.orderservice.events.channels.CustomEventChannels;
import com.devopslam.ftgo.orderservice.service.OrderService;
import com.devopslam.ftgo.orderservice.service.RestaurantOrderService;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateEventHandler {

    private final Logger logger = LoggerFactory.getLogger(OrderCreateEventHandler.class);

    @Autowired
    private CustomEventChannels eventChannels;

    @Autowired
    private RestaurantOrderService restOrderService;

    @Autowired
    private OrderService orderService;

    @StreamListener(CustomEventChannels.ORDER_CREATE_IN_CHANNEL)
    public void createOrderSink(IMessageEvent msg) {
        logger.info("Received event from {} with status {} and order Id: {}",
                msg.getServiceEvent(), msg.getStatus(), msg.getOrderId());

        switch (msg.getServiceEvent()) {
            case ACCOUNT_SERVICE:
                if (msg.getStatus().equals(MessageStatusEvent.FAILURE)) {
                    orderService.cancel(msg.getOrderId());
                } else {
                    orderService.approve(msg.getOrderId());
                }
                break;
            case CONSUMER_SERIVCE:
                if (msg.getStatus().equals(MessageStatusEvent.FAILURE)) {
                    orderService.cancel(msg.getOrderId());
                }
                break;
            case RESTAURANT_SERVICE:
                if (msg.getStatus().equals(MessageStatusEvent.FAILURE)) {
                    orderService.cancel(msg.getOrderId());
                } else {
                    CreateRestaurantOrder restaurantOrder = (CreateRestaurantOrder) msg.getDataEvent();

                    RestaurantOrder restOrder = new RestaurantOrder(
                            restaurantOrder.getId(),
                            restaurantOrder.getRestaurantId(),
                            restaurantOrder.getLineItems(),
                            restaurantOrder.getState());

                    restOrderService.putRestaurantOrderToRedis(restOrder);
                }
                break;
            default:
                logger.error("Receives whatever!!!");
        }
    }
}
