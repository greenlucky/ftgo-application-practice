package com.devopslam.ftgo.orderservice.service;

import com.devopslam.ftgo.orderservice.domain.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void getRestaurantById() {

        String restaurantId = "6fbc54d4-efaa-49f6-a643-0459fac7de75";
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        assertNotNull(restaurant.get());
    }
}