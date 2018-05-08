package com.devopslam.ftgo.restaurantservice.controllers;

import com.devopslam.ftgo.restaurantservice.domain.CreateRestaurantResponse;
import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestaurantController.URL_V1_RESTAURANT)
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    public static final String URL_V1_RESTAURANT = "/v1/restaurants";

    @Autowired
    private RestaurantService restService;


    @PostMapping
    public CreateRestaurantResponse create(@RequestBody CreateRestaurantRequest request) {
        logger.info("Create restaurant request: {}", request.toString());
        Restaurant restaurant = restService.create(request);
        return new CreateRestaurantResponse(restaurant.getId());
    }

}
