package com.devopslam.ftgo.restaurantservice.domain;

public class CreateRestaurantResponse {

    private String id;

    public CreateRestaurantResponse() {
    }

    public CreateRestaurantResponse(String id) {
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
        return "CreateRestaurantResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
