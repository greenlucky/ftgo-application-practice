package com.devopslam.ftgo.orderservice.repository.restaurantservice;

import com.devopslam.ftgo.orderservice.domain.Restaurant;
import com.devopslam.ftgo.orderservice.domain.RestaurantOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "restaurant-service")
public interface RestaurantServiceProxy {

    @GetMapping("/v1/restaurants/{restaurantId}")
    Restaurant getRestaurantById(@PathVariable("restaurantId") String restaurantId);

    @GetMapping("/v1/restaurant-orders/{restaurantOrderId}")
    RestaurantOrder getRestaurantOrderById(@PathVariable("restaurantOrderId") String restaurantorderId);
}
