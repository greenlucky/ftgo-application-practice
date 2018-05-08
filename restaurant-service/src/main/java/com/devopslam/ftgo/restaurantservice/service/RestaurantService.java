package com.devopslam.ftgo.restaurantservice.service;

import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restRepository;

    @Transactional
    public Restaurant create(CreateRestaurantRequest request) {
        String id = UUID.randomUUID().toString();
        logger.info("Create restaurant with id: {}", id);
        Restaurant restaurant = new Restaurant(request.getName(), request.getMenu());
        restaurant.setId(id);
        restRepository.save(restaurant);
        return restaurant;
    }
}
