package com.devopslam.ftgo.orderservice.events;

import com.devopslam.common.domain.Money;
import com.devopslam.common.events.IDataEvent;

import java.util.List;

public class OrderCreateEvent implements IDataEvent {

    private OrderDetails orderDetails;

    public OrderCreateEvent() {
    }

    public OrderCreateEvent(String orderId, String restaurantId, String customerId, List<OrderLineItem> orderLineItems, Money orderTotal) {
        this.orderDetails = new OrderDetails(orderId, orderLineItems, orderTotal, restaurantId, customerId);
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public String getOrderId() {
        return (orderDetails != null) ? orderDetails.getOrderId() : null;
    }

    public String getRestaurantId() {
        return (orderDetails != null) ? orderDetails.getRestaurantId() : null;
    }

    public List<OrderLineItem> getLineItems() {
        return (orderDetails != null) ? orderDetails.getLineItems() : null;
    }

    public String getConsumerId() {
        return (orderDetails != null) ? orderDetails.getConsumerId() : null;
    }

    public Money getTotal() {
        return (orderDetails != null) ? orderDetails.getOrderTotal() : null;
    }

    public PaymentInformation getPaymentInformation() {
        return (orderDetails != null) ? orderDetails.getPaymentInformation() : null;
    }
}
