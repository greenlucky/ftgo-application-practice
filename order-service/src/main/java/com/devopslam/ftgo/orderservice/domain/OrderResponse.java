package com.devopslam.ftgo.orderservice.domain;


import com.devopslam.common.domain.Money;

public class OrderResponse {
    private String orderId;
    private String state;
    private Money orderTotal;

    public OrderResponse() {
    }

    public OrderResponse(String orderId, String state, Money orderTotal) {
        this.orderId = orderId;
        this.state = state;
        this.orderTotal = orderTotal;
    }

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.state = order.getState().name();
        this.orderTotal = order.getOrderTotal();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Money orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", state='" + state + '\'' +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
