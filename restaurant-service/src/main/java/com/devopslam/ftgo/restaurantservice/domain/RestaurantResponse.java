package com.devopslam.ftgo.restaurantservice.domain;

public class RestaurantResponse {

    private String id;

    public RestaurantResponse() {
    }

    public RestaurantResponse(String id) {
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
        return "RestaurantResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
