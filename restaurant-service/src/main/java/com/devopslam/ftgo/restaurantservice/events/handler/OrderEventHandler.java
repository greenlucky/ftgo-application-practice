package com.devopslam.ftgo.restaurantservice.events.handler;

import com.devopslam.common.events.IMessageEvent;
import com.devopslam.common.events.MessageEvent;
import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;
import com.devopslam.ftgo.orderservice.events.OrderCreateEvent;
import com.devopslam.ftgo.orderservice.events.OrderState;
import com.devopslam.ftgo.orderservice.events.OrderStateEvent;
import com.devopslam.ftgo.restaurantservice.domain.RestaurantOrder;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantOrder;
import com.devopslam.ftgo.restaurantservice.events.RestaurantLineItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantOrderState;
import com.devopslam.ftgo.restaurantservice.events.channels.CustomEventChannels;
import com.devopslam.ftgo.restaurantservice.service.RestaurantOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEventHandler {

    private final Logger logger = LoggerFactory.getLogger(OrderEventHandler.class);

    @Autowired
    private RestaurantOrderService restaurantOrderService;

    @StreamListener(CustomEventChannels.ORDER_CREATE_IN_CHANNEL)
    @SendTo(CustomEventChannels.ORDER_CREATE_OUT_CHANNEL)
    public IMessageEvent orderCreateSink(IMessageEvent msg) {
        logger.info("Received event from {} with order Id: {}", msg.getServiceEvent(), msg.getOrderId());
        String orderId = msg.getOrderId();
        try {
            if (msg.getDataEvent() == null || !(msg.getDataEvent() instanceof OrderCreateEvent)) return null;
            OrderCreateEvent event = (OrderCreateEvent) msg.getDataEvent();

            List<RestaurantLineItem> lineItems = event.getLineItems().stream().map(it -> new RestaurantLineItem(
                    it.getMenuItemId(),
                    it.getName(),
                    it.getQuantity()))
                    .collect(Collectors.toList());

            RestaurantOrder resOrder = restaurantOrderService.createRestaurantOrder(
                    event.getRestaurantId(),
                    event.getOrderId(),
                    lineItems);

            CreateRestaurantOrder restOrderEvent = new CreateRestaurantOrder(
                    resOrder.getId(),
                    resOrder.getRestaurantId(),
                    resOrder.getLineItems(),
                    resOrder.getState());
            return new MessageEvent(orderId, ServiceEvent.RESTAURANT_SERVICE,
                    restOrderEvent, MessageStatusEvent.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new MessageEvent(orderId, ServiceEvent.RESTAURANT_SERVICE, MessageStatusEvent.FAILURE);
        }
    }

    @StreamListener(CustomEventChannels.ORDER_RES_CHANNEL)
    public void createdRestaurantOrderSink(IMessageEvent msg) {

        try {
            if (msg.getDataEvent() == null || !(msg.getDataEvent() instanceof OrderStateEvent)) {
                logger.error("Received whatever!!!");
                return;
            }

            OrderStateEvent event = (OrderStateEvent) msg.getDataEvent();

            logger.info("Received event from {} with order stage: {} and order Id: {}",
                    msg.getServiceEvent(), event.getState(), event.getOrderId());

            RestaurantOrderState restaurantOrderState = RestaurantOrderState.CREATED;
            if (event.getState().equals(OrderState.CANCELLED)) {
                restaurantOrderState = RestaurantOrderState.CANCELLED;
            }

            restaurantOrderService.changeStateResOrder(event.getOrderId(), restaurantOrderState);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
