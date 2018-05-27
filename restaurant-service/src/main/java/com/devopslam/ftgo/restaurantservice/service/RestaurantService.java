package com.devopslam.ftgo.restaurantservice.service;

import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.UpdateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.handler.RestaurantSender;
import com.devopslam.ftgo.restaurantservice.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restRepository;

    @Autowired
    private RestaurantSender sender;


    @Transactional
    public Restaurant create(CreateRestaurantRequest request) {
        String id = UUID.randomUUID().toString();
        logger.info("Create restaurant with id: {}", id);
        Restaurant restaurant = new Restaurant(id, request.getName(), request.getMenuItems());
        restRepository.save(restaurant);
        return restaurant;
    }

    @Transactional
    public Restaurant update(UpdateRestaurantRequest request) {
        logger.info("Update restaurant with id: {}", request.getId());
        Restaurant restaurant = new Restaurant(request.getId(), request.getName(), request.getMenuItems());
        restRepository.save(restaurant);
        return restaurant;
    }

    @Transactional
    public void delete(String restaurantId) {
        logger.info("Delete restaurant with Id: {}", restaurantId);
        restRepository.delete(restaurantId);
    }

    public Optional<Restaurant> getRestaurant(String restaurantId) {
        return restRepository.findById(restaurantId);
    }

}
