package com.devopslam.ftgo.restaurantservice.events;


import javax.persistence.ElementCollection;
import java.util.List;

public  abstract class RestaurantRequest {

    private String name;
    @ElementCollection
    private List<MenuItem> menuItems;

    public RestaurantRequest() {
    }

    public RestaurantRequest(String name, List<MenuItem> menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "CreateRestaurantRequest{" +
                "name='" + name + '\'' +
                ", menuItems=" + menuItems +
                '}';
    }
}
