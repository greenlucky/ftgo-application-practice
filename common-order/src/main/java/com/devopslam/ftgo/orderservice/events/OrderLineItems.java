package com.devopslam.ftgo.orderservice.events;

import com.devopslam.common.domain.Money;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Embeddable
public class OrderLineItems {

    @Embedded
    @ElementCollection
    @CollectionTable(name = "order_line_times")
    private List<OrderLineItem> orderLineItems;

    public OrderLineItems() {
    }

    public OrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public Money orderTotal() {
        return orderLineItems.stream().map(OrderLineItem::getTotal).reduce(Money.ZERO, Money::add);
    }

    @Override
    public String toString() {
        return "OrderLineItems{" +
                "orderLineItems=" + orderLineItems +
                "orderTotal=" + orderTotal() +
                '}';
    }
}
