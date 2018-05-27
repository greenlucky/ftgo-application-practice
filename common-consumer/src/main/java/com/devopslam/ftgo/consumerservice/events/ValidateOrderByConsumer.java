package com.devopslam.ftgo.consumerservice.events;

import com.devopslam.common.domain.Money;
import com.devopslam.common.events.IDataEvent;
import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;

public class ValidateOrderByConsumer {

    private String consumerId;
    private String orderId;
    private Money orderTotal;

    public ValidateOrderByConsumer() {
    }

    public ValidateOrderByConsumer(String consumerId, String orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public ServiceEvent getServiceEvent() {
        return null;
    }

    public IDataEvent getDataEvent() {
        return null;
    }

    public MessageStatusEvent getStatus() {
        return null;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }
}
