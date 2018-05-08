package com.devopslam.ftgo.restaurantservice.events;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class RestaurantMenu {

    @ElementCollection
    private List<MenuItem> menuItems;

    public RestaurantMenu() {
    }

    public RestaurantMenu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "RestaurantMenu{" +
                "menuItems=" + menuItems +
                '}';
    }
}
