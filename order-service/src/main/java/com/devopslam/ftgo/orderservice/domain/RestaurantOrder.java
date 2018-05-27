package com.devopslam.ftgo.orderservice.domain;

import com.devopslam.ftgo.restaurantservice.events.RestaurantLineItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantOrderState;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.List;

@RedisHash(value = "restaurant_orders")
public class RestaurantOrder extends com.devopslam.ftgo.restaurantservice.events.RestaurantOrder {


    public RestaurantOrder() {
    }

    public RestaurantOrder(String id, String restaurantId, List<RestaurantLineItem> lineItems, RestaurantOrderState state) {
        super(id, restaurantId, lineItems, state);
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }
}
