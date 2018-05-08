package com.devopslam.ftgo.orderservice.domain;

import com.devopslam.ftgo.orderservice.events.MenuItem;

import java.util.List;
import java.util.Optional;

public class Restaurant {

    private String id;
    private String name;
    private List<MenuItem> menuItems;

    public Restaurant() {
    }

    public Restaurant(String id, String name, List<MenuItem> menuItems) {
        this.id = id;
        this.name = name;
        this.menuItems = menuItems;
    }

    public Optional<MenuItem> getMenuItem(String menuItemId) {
        return menuItems.stream().filter(mi -> mi.getId().equals(menuItemId)).findFirst();
    }
}
