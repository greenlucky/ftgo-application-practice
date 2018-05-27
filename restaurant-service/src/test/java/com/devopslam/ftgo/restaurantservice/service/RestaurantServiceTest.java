package com.devopslam.ftgo.restaurantservice.service;

import com.devopslam.common.domain.Money;
import com.devopslam.ftgo.restaurantservice.BaseTest;
import com.devopslam.ftgo.restaurantservice.controllers.RestaurantController;
import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.domain.RestaurantResponse;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.MenuItem;
import com.devopslam.ftgo.restaurantservice.events.UpdateRestaurantRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class RestaurantServiceTest extends BaseTest {

    @Autowired
    private RestaurantService restaurantService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    List<MenuItem> menuItems;

    @Before
    public void init() {
        menuItems = Arrays.asList(
                new MenuItem(UUID.randomUUID().toString(), "Fish with price", new Money(2)),
                new MenuItem(UUID.randomUUID().toString(), "Fish with eggs", new Money("2.2")),
                new MenuItem(UUID.randomUUID().toString(), "Fish with tomato", new Money(1))
        );
    }

    @Test
    public void create_restaurant() {

        CreateRestaurantRequest restaurant = new CreateRestaurantRequest("MNU", menuItems);
        Restaurant result = restaurantService.create(restaurant);
        System.out.println(result);
    }

    @Test
    public void update_restaurant() throws InterruptedException {
        String id = "6fbc54d4-efaa-49f6-a643-0459fac7de75";
        UpdateRestaurantRequest restaurant = new UpdateRestaurantRequest(id, "MNU", menuItems);
        Restaurant result = restaurantService.update(restaurant);
        System.out.println(result);
        Thread.sleep(2000);
    }

    @Test
    public void delete_restaurant() {
        String id = "6fbc54d4-efaa-49f6-a643-0459fac7de75";
        restaurantService.delete(id);
    }

    @Test
    public void controller_restaurant_create() {
        String URL = "http://localhost:" + port + RestaurantController.URL_V1_RESTAURANT;
        CreateRestaurantRequest request = new CreateRestaurantRequest("MNU", menuItems);
        ResponseEntity<RestaurantResponse> response = restTemplate.postForEntity(URL, request, RestaurantResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

}