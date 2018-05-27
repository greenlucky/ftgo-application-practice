package com.devopslam.ftgo.orderservice.events;

import com.devopslam.common.domain.Money;

import javax.persistence.Embeddable;

@Embeddable
public class OrderLineItem {
    private int quantity;
    private String menuItemId;
    private String name;
    private Money price;

    public OrderLineItem() {
    }

    public OrderLineItem(String menuItemId, String name, Money price, int quantity) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money getTotal() {
        return price.multiply(quantity);
    }
}
