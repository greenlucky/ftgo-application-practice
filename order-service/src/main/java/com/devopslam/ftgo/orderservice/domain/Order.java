package com.devopslam.ftgo.orderservice.domain;

import com.devopslam.common.Money;
import com.devopslam.ftgo.orderservice.events.OrderLineItem;
import com.devopslam.ftgo.orderservice.events.OrderState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @NotNull
    private String customerId;

    @NotNull
    private String restaurantId;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "order_line_times")
    private List<OrderLineItem> orderLineItems;

    @Embedded
    private PaymentInformation paymentInformation;

    @Embedded
    private DeliveryInformation deliveryInformation;

    @Embedded
    private Money orderMinimum = new Money(Integer.MAX_VALUE);

    public Order() {
    }

    public Order(String customerId, String restaurantId, List<OrderLineItem> orderLineItems) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLineItems = orderLineItems;
    }

    public Order(String orderId, String customerId, String restaurantId, List<OrderLineItem> orderLineItems) {
        this.id = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLineItems = orderLineItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public DeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public Money getOrderMinimun() {
        return orderMinimum;
    }

    public void setOrderMinimun(Money orderMinimum) {
        this.orderMinimum = orderMinimum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", state=" + state +
                ", customerId='" + customerId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", orderLineItems=" + orderLineItems +
                ", paymentInformation=" + paymentInformation +
                ", deliveryInformation=" + deliveryInformation +
                ", orderMinimun=" + orderMinimum +
                '}';
    }
}
