package com.devopslam.ftgo.orderservice.domain;

import com.devopslam.ftgo.orderservice.events.MenuItem;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RedisHash("restaurants")
public class Restaurant {

    @Id
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
