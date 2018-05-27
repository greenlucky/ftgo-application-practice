package com.devopslam.ftgo.restaurantservice.events;

import java.util.List;

public class CreateRestaurantOrder extends RestaurantOrder {

    public CreateRestaurantOrder() {
    }

    public CreateRestaurantOrder(String id, String restaurantId, List<RestaurantLineItem> lineItems, RestaurantOrderState state) {
        super(id, restaurantId, lineItems, state);
    }
}
