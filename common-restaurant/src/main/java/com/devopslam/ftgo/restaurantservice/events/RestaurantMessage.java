package com.devopslam.ftgo.restaurantservice.events;

public class RestaurantMessage {
    private String id;

    public RestaurantMessage() {
    }

    public RestaurantMessage(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RestaurantMessage{" +
                "id='" + id + '\'' +
                '}';
    }
}
