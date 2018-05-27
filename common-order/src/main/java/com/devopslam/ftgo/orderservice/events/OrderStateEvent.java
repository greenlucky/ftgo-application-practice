package com.devopslam.ftgo.orderservice.events;

import com.devopslam.common.events.IDataEvent;

public class OrderStateEvent implements IDataEvent {
    private String orderId;
    private OrderState state;

    public OrderStateEvent() {
    }

    public OrderStateEvent(String orderId, OrderState state) {
        this.orderId = orderId;
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
