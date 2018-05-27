package com.devopslam.ftgo.orderservice.service;

import com.devopslam.ftgo.orderservice.domain.LineItem;
import com.devopslam.ftgo.orderservice.domain.Order;
import com.devopslam.ftgo.orderservice.domain.OrderRevision;
import com.devopslam.ftgo.orderservice.domain.Restaurant;
import com.devopslam.ftgo.orderservice.events.MenuItem;
import com.devopslam.ftgo.orderservice.events.OrderLineItem;
import com.devopslam.ftgo.orderservice.events.OrderState;
import com.devopslam.ftgo.orderservice.events.OrderStateEvent;
import com.devopslam.ftgo.orderservice.events.handlers.OrderCreateSendOut;
import com.devopslam.ftgo.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderCreateSendOut sendOut;

    @Transactional
    public Order createOrder(String customerId, String restaurantId, List<LineItem> lineItems) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        if (!restaurant.isPresent()) throw new RuntimeException("Restaurant not found: " + restaurantId);
        String orderId = UUID.randomUUID().toString();
        logger.info("Create new order with Id: {}", orderId);
        List<OrderLineItem> orderLineItems = makeOrderLineItems(lineItems, restaurant.get());
        Order order = new Order(orderId, customerId, restaurantId, orderLineItems);
        orderRepository.save(order);
        return order;
    }

    private List<OrderLineItem> makeOrderLineItems(List<LineItem> lineItems, Restaurant restaurant) {
        return lineItems.stream().map(li -> {
            MenuItem mi = restaurant.getMenuItem(li.getMenuItemId()).orElseThrow(()
                    -> new RuntimeException("MenuItem not found: " + li.getMenuItemId()));
            return new OrderLineItem(li.getMenuItemId(), mi.getName(), mi.getPrice(), li.getQuantity());
        }).collect(Collectors.toList());
    }

    public Optional<Order> getOrder(String orderId) {
        return Optional.ofNullable(orderRepository.getOne(orderId));
    }

    @Transactional
    public Optional<Order> cancel(String orderId) {
        OrderState state = OrderState.CANCELLED;
        Optional<Order> order = changeOrderStatus(orderId, state);
        sendOut.publishCancelRestaurantOrder(orderId, new OrderStateEvent(orderId, state));
        return order;
    }

    public Optional<Order> reviseOrder(String orderId, OrderRevision orderRevision) {
        Optional<Order> order = Optional.ofNullable(orderRepository.getOne(orderId));
        if (!order.isPresent()) {
            logger.error("Cannot find order: {}", orderId);
            return order;
        }
        return null;
    }

    @Transactional
    public Optional<Order> approve(String orderId) {
        OrderState state = OrderState.APPROVED;
        Optional<Order> order =  changeOrderStatus(orderId, state);
        sendOut.publishCancelRestaurantOrder(orderId, new OrderStateEvent(orderId, state));
        return order;
    }

    public Optional<Order> changeOrderStatus(String orderId, OrderState orderState) {
        Optional<Order> order = Optional.ofNullable(orderRepository.getOne(orderId));
        if (!order.isPresent()) {
            logger.error("Cannot find order: {}", orderId);
            throw new RuntimeException("Not found order Id: {}" + orderId);
        }

        order.get().setState(orderState);
        orderRepository.save(order.get());
        return order;
    }
}
