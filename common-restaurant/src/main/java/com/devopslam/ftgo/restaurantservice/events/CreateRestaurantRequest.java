package com.devopslam.ftgo.restaurantservice.events;


import java.util.List;

public class CreateRestaurantRequest extends RestaurantRequest {

    public CreateRestaurantRequest() {
    }

    public CreateRestaurantRequest(String name, List<MenuItem> menuItems) {
        super(name, menuItems);
    }
}
