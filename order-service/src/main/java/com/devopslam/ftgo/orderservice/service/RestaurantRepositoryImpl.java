package com.devopslam.ftgo.orderservice.service;

import com.devopslam.common.Money;
import com.devopslam.ftgo.orderservice.domain.Restaurant;
import com.devopslam.ftgo.orderservice.events.MenuItem;
import com.devopslam.ftgo.orderservice.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Override
    public Optional<Restaurant> findOne(String restaurantId) {
        List<MenuItem> menuItems = Arrays.asList(new MenuItem("123456", "Fish", new Money(1)));
        return Optional.of(new Restaurant("12345", "MUN", menuItems));
    }
}
