package com.devopslam.ftgo.restaurantservice.events;

import com.devopslam.common.events.IDataEvent;
import com.devopslam.ftgo.restaurantservice.events.inf.IRestaurantOrder;

import java.util.List;

public abstract class RestaurantOrder implements IDataEvent {

    private String id;

    private RestaurantOrderState state;

    private RestaurantOrderState previousState;

    private String restaurantId;

    private List<RestaurantLineItem> lineItems;

    private Long readyBy;
    private Long acceptTime;
    private Long preparingTime;
    private Long pickUpTime;
    private Long readyForPickUpTime;

    public RestaurantOrder() {
    }

    public RestaurantOrder(String id, String restaurantId, List<RestaurantLineItem> lineItems, RestaurantOrderState state) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.lineItems = lineItems;
        this.state = state;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RestaurantOrderState getState() {
        return state;
    }

    public void setState(RestaurantOrderState state) {
        this.state = state;
    }

    public RestaurantOrderState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(RestaurantOrderState previousState) {
        this.previousState = previousState;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<RestaurantLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<RestaurantLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Long getReadyBy() {
        return readyBy;
    }

    public void setReadyBy(Long readyBy) {
        this.readyBy = readyBy;
    }

    public Long getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Long acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Long getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(Long preparingTime) {
        this.preparingTime = preparingTime;
    }

    public Long getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Long pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Long getReadyForPickUpTime() {
        return readyForPickUpTime;
    }

    public void setReadyForPickUpTime(Long readyForPickUpTime) {
        this.readyForPickUpTime = readyForPickUpTime;
    }
}
