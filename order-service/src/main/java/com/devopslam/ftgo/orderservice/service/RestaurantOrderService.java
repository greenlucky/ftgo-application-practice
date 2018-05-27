package com.devopslam.ftgo.orderservice.service;

import com.devopslam.ftgo.orderservice.domain.RestaurantOrder;
import com.devopslam.ftgo.orderservice.repository.redis.RDRestaurantOrderRepository;
import com.devopslam.ftgo.orderservice.repository.restaurantservice.RestaurantServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantOrderService {

    @Autowired
    private RDRestaurantOrderRepository resOrderRepository;

    @Autowired
    private RestaurantServiceProxy resServiceProxy;

    public Optional<RestaurantOrder> getRestaurantOrder(String orderId) {
        Optional<RestaurantOrder> restaurantOrder = Optional.ofNullable(resOrderRepository.findOne(orderId));
        if (restaurantOrder.isPresent()) return restaurantOrder;
        restaurantOrder = Optional.ofNullable(resServiceProxy.getRestaurantOrderById(orderId));
        if (restaurantOrder.isPresent()) resOrderRepository.save(restaurantOrder.get());
        return restaurantOrder;
    }

    public void putRestaurantOrderToRedis(RestaurantOrder restaurantOrder) {
        resOrderRepository.save(restaurantOrder);
    }
}
