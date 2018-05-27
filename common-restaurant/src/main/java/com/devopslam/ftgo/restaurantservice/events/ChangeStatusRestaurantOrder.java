package com.devopslam.ftgo.restaurantservice.events;

import com.devopslam.common.events.IDataEvent;

public class ChangeStatusRestaurantOrder implements IDataEvent {

    private String orderId;
    private RestaurantOrderState state;

    public ChangeStatusRestaurantOrder() {
    }

    public ChangeStatusRestaurantOrder(String orderId, RestaurantOrderState state) {
        this.orderId = orderId;
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public RestaurantOrderState getState() {
        return state;
    }

    public void setState(RestaurantOrderState state) {
        this.state = state;
    }
}
