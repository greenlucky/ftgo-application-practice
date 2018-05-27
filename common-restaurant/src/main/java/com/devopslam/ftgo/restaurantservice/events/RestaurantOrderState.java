package com.devopslam.ftgo.restaurantservice.events;

public enum RestaurantOrderState {
    CREATE_PENDING, CREATED, ACCEPTED, PREPARING, READY_FOR_PICKUP, PICKED_UP, CANCEL_PENDING, CANCELLED, REVISION_PENDING,
}
