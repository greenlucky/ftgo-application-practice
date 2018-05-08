package com.devopslam.ftgo.restaurantservice.service;

import com.devopslam.common.Money;
import com.devopslam.ftgo.restaurantservice.controllers.RestaurantController;
import com.devopslam.ftgo.restaurantservice.domain.CreateRestaurantResponse;
import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.MenuItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantMenu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    RestaurantMenu restaurantMenu;

    @Before
    public void init() {
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(UUID.randomUUID().toString(), "Fish with price", new Money(2)),
                new MenuItem(UUID.randomUUID().toString(), "Fish with eggs", new Money("2.2")),
                new MenuItem(UUID.randomUUID().toString(), "Fish with tomato", new Money(1))
        );
        restaurantMenu = new RestaurantMenu(menuItems);
    }

    @Test
    public void create_restaurant() {

        CreateRestaurantRequest restaurant = new CreateRestaurantRequest("MNU", restaurantMenu);
        Restaurant result = restaurantService.create(restaurant);
        System.out.println(result);
    }

    @Test
    public void controller_restaurant_create() {
        String URL = "http://localhost:" + port + RestaurantController.URL_V1_RESTAURANT;
        CreateRestaurantRequest request = new CreateRestaurantRequest("MNU", restaurantMenu);
        ResponseEntity<CreateRestaurantResponse> response = restTemplate.postForEntity(URL, request, CreateRestaurantResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

}