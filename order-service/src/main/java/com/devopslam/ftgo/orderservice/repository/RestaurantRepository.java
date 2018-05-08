package com.devopslam.ftgo.orderservice.repository;

import com.devopslam.ftgo.orderservice.domain.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository {
    Optional<Restaurant> findOne(String restaurantId);
}
