package com.devopslam.ftgo.orderservice.controllers;

import com.devopslam.common.domain.Money;
import com.devopslam.ftgo.orderservice.domain.*;
import com.devopslam.ftgo.orderservice.events.OrderCreateEvent;
import com.devopslam.ftgo.orderservice.events.OrderLineItem;
import com.devopslam.ftgo.orderservice.events.handlers.OrderCreateSendOut;
import com.devopslam.ftgo.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(OrderController.URL_V1_ORDER)
public class OrderController {

    public static final String URL_V1_ORDER = "/v1/orders";

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderCreateSendOut sendOut;

    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(
                request.getConsumerId(),
                request.getRestaurantId(),
                request.getLineItems());
        return new CreateOrderResponse(order.getId());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderId) {
        Optional<Order> order = orderService.getOrder(orderId);
        if (!order.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new OrderResponse(order.get()), HttpStatus.OK);
    }

    @GetMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable String orderId) {
        Optional<Order> order = orderService.cancel(orderId);
        if (!order.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new OrderResponse(order.get()), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> revise(@PathVariable String orderId,
                                                @RequestBody ReviseOderRequest request) {
        Optional<Order> order = orderService.reviseOrder(orderId,
                new OrderRevision(Optional.empty(), request.getRevisedLineItemQuantities()));
        if (!order.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new OrderResponse(order.get()), HttpStatus.OK);
    }

    @GetMapping("/sendout")
    public void sendOutOrderEvent() {
        String customerId = "9053abe0-6d91-4913-9df9-73350a80e10e";
        String restaurantId = "6fbc54d4-efaa-49f6-a643-0459fac7de75";
        List<OrderLineItem> lineItems = new ArrayList<>();
        lineItems.add(new OrderLineItem("062dbcbe-6608-47bc-8a40-7ec766bd4169", "ABC", new Money(2), 2));


        List<LineItem> lineItems1 = new ArrayList<>();
        lineItems1.add(new LineItem("062dbcbe-6608-47bc-8a40-7ec766bd4169", 2));

        Order order = orderService.createOrder(customerId, restaurantId, lineItems1);
        String orderId = order.getId();
        OrderCreateEvent event = new OrderCreateEvent(orderId, restaurantId, customerId, lineItems, new Money(20));
        sendOut.publishOrderCreate(event);
    }

}
