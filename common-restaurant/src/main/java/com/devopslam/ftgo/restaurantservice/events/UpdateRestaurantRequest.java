package com.devopslam.ftgo.restaurantservice.events;

import java.util.List;

public class UpdateRestaurantRequest extends RestaurantRequest {

    private String id;

    public UpdateRestaurantRequest() {
    }

    public UpdateRestaurantRequest(String id) {
        this.id = id;
    }

    public UpdateRestaurantRequest(String id, String name, List<MenuItem> menuItems) {
        super(name, menuItems);
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
        return "UpdateRestaurantRequest{" +
                "id='" + id + '\'' +
                '}';
    }
}
