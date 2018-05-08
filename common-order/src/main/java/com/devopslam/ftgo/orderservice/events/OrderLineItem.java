package com.devopslam.ftgo.orderservice.events;

import com.devopslam.common.Money;

import javax.persistence.Embeddable;

@Embeddable
public class OrderLineItem {
    private int quantity;
    private String menuItemId;
    private String name;

    public OrderLineItem() {
    }

    public OrderLineItem(String menuItemId, String name, Money price, int quantity) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
