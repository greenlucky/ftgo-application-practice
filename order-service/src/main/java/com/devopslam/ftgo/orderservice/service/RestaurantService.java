package com.devopslam.ftgo.orderservice.service;

import com.devopslam.ftgo.orderservice.domain.Restaurant;
import com.devopslam.ftgo.orderservice.repository.restaurantservice.RestaurantServiceProxy;
import com.devopslam.ftgo.orderservice.repository.redis.RDRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RDRestaurantRepository resRepository;

    @Autowired
    private RestaurantServiceProxy restaurantProxy;

    public Optional<Restaurant> getRestaurantById(String restaurantId) {
        Optional<Restaurant> restaurant = Optional.ofNullable(resRepository.findOne(restaurantId));
        if (restaurant.isPresent()) return restaurant;
        restaurant = Optional.ofNullable(restaurantProxy.getRestaurantById(restaurantId));
        if (restaurant.isPresent()) resRepository.save(restaurant.get());
        return restaurant;
    }

    public void delete(String restaurantId) {
        resRepository.delete(restaurantId);
    }
}
