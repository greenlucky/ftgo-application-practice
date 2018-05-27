package com.devopslam.ftgo.orderservice.domain;

import java.util.List;

public class CreateOrderRequest {

    private String restaurantId;
    private String consumerId;
    private List<LineItem> lineItems;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String restaurantId, String consumerId, List<LineItem> lineItems) {
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
        this.lineItems = lineItems;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }


    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "restaurantId='" + restaurantId + '\'' +
                ", consomerId='" + consumerId + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}
