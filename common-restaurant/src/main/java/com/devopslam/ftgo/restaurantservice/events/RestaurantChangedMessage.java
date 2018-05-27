package com.devopslam.ftgo.restaurantservice.events;

import com.devopslam.ftgo.restaurantservice.enums.RestaurantMessageAction;

public class RestaurantChangedMessage {

    private String method;
    private RestaurantMessageAction action;
    private String id;

    public RestaurantChangedMessage() {
    }

    public RestaurantChangedMessage(String method, RestaurantMessageAction action, String id) {
        this.method = method;
        this.action = action;
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RestaurantMessageAction getAction() {
        return action;
    }

    public void setAction(RestaurantMessageAction action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "RestaurantChangedMessage{" +
                "method='" + method + '\'' +
                ", id='" + id + '\'' +
                ", action=" + action +
                '}';
    }
}
