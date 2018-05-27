package com.devopslam.ftgo.orderservice.events.handlers;

import com.devopslam.common.events.MessageEvent;
import com.devopslam.common.events.enums.ServiceEvent;
import com.devopslam.ftgo.orderservice.domain.Order;
import com.devopslam.ftgo.orderservice.events.OrderCreateEvent;
import com.devopslam.ftgo.orderservice.events.OrderState;
import com.devopslam.ftgo.orderservice.events.OrderStateEvent;
import com.devopslam.ftgo.orderservice.events.channels.CustomEventChannels;
import com.devopslam.ftgo.restaurantservice.events.ChangeStatusRestaurantOrder;
import com.devopslam.ftgo.restaurantservice.events.RestaurantOrderState;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateSendOut {

    private final Logger logger = LoggerFactory.getLogger(OrderCreateSendOut.class);

    @Autowired
    private CustomEventChannels sendOut;

    public void publishOrderCreate(Order order) {
        logger.info("Sending Order after create with Id: {}", order.getId());
        OrderCreateEvent orderCreateEvent = new OrderCreateEvent(
                order.getId(),
                order.getRestaurantId(),
                order.getCustomerId(),
                order.getOrderLineItems().getOrderLineItems(),
                order.getOrderTotal());
        MessageEvent messageEvent = new MessageEvent(orderCreateEvent.getOrderId(), ServiceEvent.ORDER_SERVICE, orderCreateEvent);
        sendOut.orderCreateOut().send(MessageBuilder.withPayload(messageEvent).build());
    }

    public void publishOrderCreate(OrderCreateEvent orderCreateEvent) {
        logger.info("Sending Order after create with Id: {}", orderCreateEvent.getOrderId());
        MessageEvent messageEvent = new MessageEvent(orderCreateEvent.getOrderId(), ServiceEvent.ORDER_SERVICE, orderCreateEvent);
        sendOut.orderCreateOut().send(MessageBuilder.withPayload(messageEvent).build());
    }

    public void publishCancelRestaurantOrder(String orderId, OrderStateEvent event) {
        logger.info("Sending Cancel restaurant order with order Id: {}", orderId);
        MessageEvent messageEvent = new MessageEvent(orderId, ServiceEvent.ORDER_SERVICE, event);
        sendOut.orderResCreateIn().send(MessageBuilder.withPayload(messageEvent).build());
    }
}
