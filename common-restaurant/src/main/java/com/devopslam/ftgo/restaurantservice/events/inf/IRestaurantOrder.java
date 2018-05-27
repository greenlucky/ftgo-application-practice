package com.devopslam.ftgo.restaurantservice.events.inf;

import com.devopslam.common.events.IMessageEvent;
import com.devopslam.ftgo.restaurantservice.events.RestaurantLineItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantOrderState;

import java.util.List;

public interface IRestaurantOrder {

    String getId();
    
    RestaurantOrderState getState();

    RestaurantOrderState getPreviousState();

    String getRestaurantId();

    List<RestaurantLineItem> getLineItems();

    Long getReadyBy();

    Long getAcceptTime();

    Long getPreparingTime();

    Long getPickUpTime();

    Long getReadyForPickUpTime();

}
