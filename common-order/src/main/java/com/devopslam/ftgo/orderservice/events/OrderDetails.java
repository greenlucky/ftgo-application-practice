package com.devopslam.ftgo.orderservice.events;

import com.devopslam.common.domain.Money;

import java.util.List;

public class OrderDetails {

    private String orderId;
    private List<OrderLineItem> lineItems;
    private Money orderTotal;

    private String restaurantId;
    private String consumerId;
    private PaymentInformation paymentInformation;

    public OrderDetails() {
    }

    public OrderDetails(String orderId, List<OrderLineItem> lineItems, Money orderTotal, String restaurantId, String consumerId) {
        this.orderId = orderId;
        this.lineItems = lineItems;
        this.orderTotal = orderTotal;
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", lineItems=" + lineItems +
                ", orderTotal=" + orderTotal +
                ", restaurantId='" + restaurantId + '\'' +
                ", consumerId='" + consumerId + '\'' +
                '}';
    }
}
