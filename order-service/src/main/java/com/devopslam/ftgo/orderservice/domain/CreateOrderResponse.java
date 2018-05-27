package com.devopslam.ftgo.orderservice.domain;

public class CreateOrderResponse {

    private String orderId;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CreateOrderResponse{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
