package com.devopslam.ftgo.restaurantservice.domain;

import com.devopslam.ftgo.restaurantservice.events.RestaurantLineItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantOrderState;
import com.devopslam.ftgo.restaurantservice.events.inf.IRestaurantOrder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant_orders")
public class RestaurantOrder implements IRestaurantOrder {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private RestaurantOrderState state = RestaurantOrderState.CREATE_PENDING;

    private RestaurantOrderState previousState;

    private String restaurantId;

    @ElementCollection
    @CollectionTable(name = "restaurant_order_line_items")
    private List<RestaurantLineItem> lineItems;

    private Long readyBy;
    private Long acceptTime;
    private Long preparingTime;
    private Long pickUpTime;
    private Long readyForPickUpTime;

    public RestaurantOrder() {
    }

    public RestaurantOrder(String id, String restaurantId, List<RestaurantLineItem> lineItems) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.lineItems = lineItems;
    }

    public String getId() {
        return id;
    }

    public RestaurantOrderState getState() {
        return state;
    }

    public RestaurantOrderState getPreviousState() {
        return previousState;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public List<RestaurantLineItem> getLineItems() {
        return lineItems;
    }

    public Long getReadyBy() {
        return readyBy;
    }

    public Long getAcceptTime() {
        return acceptTime;
    }

    public Long getPreparingTime() {
        return preparingTime;
    }

    public Long getPickUpTime() {
        return pickUpTime;
    }

    public Long getReadyForPickUpTime() {
        return readyForPickUpTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setState(RestaurantOrderState state) {
        this.state = state;
    }

    public void setPreviousState(RestaurantOrderState previousState) {
        this.previousState = previousState;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setLineItems(List<RestaurantLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void setReadyBy(Long readyBy) {
        this.readyBy = readyBy;
    }

    public void setAcceptTime(Long acceptTime) {
        this.acceptTime = acceptTime;
    }

    public void setPreparingTime(Long preparingTime) {
        this.preparingTime = preparingTime;
    }

    public void setPickUpTime(Long pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public void setReadyForPickUpTime(Long readyForPickUpTime) {
        this.readyForPickUpTime = readyForPickUpTime;
    }

    @Override
    public String toString() {
        return "RestaurantOrder{" +
                "id='" + id + '\'' +
                ", state=" + state +
                ", previousState=" + previousState +
                ", restaurantId='" + restaurantId + '\'' +
                ", lineItems=" + lineItems +
                ", readyBy=" + readyBy +
                ", acceptTime=" + acceptTime +
                ", preparingTime=" + preparingTime +
                ", pickUpTime=" + pickUpTime +
                ", readyForPickUpTime=" + readyForPickUpTime +
                '}';
    }
}
