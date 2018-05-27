package com.devopslam.ftgo.restaurantservice.controllers;

import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.domain.RestaurantResponse;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.UpdateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(RestaurantController.URL_V1_RESTAURANT)
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    public static final String URL_V1_RESTAURANT = "/v1/restaurants";

    @Autowired
    private RestaurantService restService;

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable String restaurantId) {
        logger.info("Get Restaurant by Id: {}", restaurantId);
        Optional<Restaurant> restaurant = restService.getRestaurant(restaurantId);
        if (!restaurant.isPresent()) throw new RuntimeException("Not found restaurant Id: " + restaurantId);
        logger.info("Restaurant: {}", restaurant.get().toString());
        return restaurant.get();
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        logger.info("Create restaurant request: {}", request.toString());
        Restaurant restaurant = restService.create(request);
        return new ResponseEntity<>(new RestaurantResponse(restaurant.getId()), HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@PathVariable("restaurantId") String restaurantId,
                                                               @RequestBody UpdateRestaurantRequest request) {
        Optional<Restaurant> restaurant = restService.getRestaurant(restaurantId);
        if (!restaurant.isPresent()) throw new RuntimeException("Not found restaurant Id: " + restaurantId);
        restService.update(request);
        return new ResponseEntity<>(new RestaurantResponse(restaurantId), HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> deleteRestaurant(@PathVariable("restaurantId") String restaurantId) {
        Optional<Restaurant> restaurant = restService.getRestaurant(restaurantId);
        if (!restaurant.isPresent()) throw new RuntimeException("Not found restaurant Id: " + restaurantId);
        restService.delete(restaurantId);
        return new ResponseEntity<>(new RestaurantResponse(restaurantId), HttpStatus.OK);
    }

}
