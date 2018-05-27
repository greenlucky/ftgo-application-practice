package com.devopslam.common.events;

import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;

public class MessageEvent implements IMessageEvent {

    private String orderId;
    private ServiceEvent serviceEvent = ServiceEvent.NONE;
    private IDataEvent dataEvent;
    private MessageStatusEvent statusEvent;

    public MessageEvent() {
    }

    public MessageEvent(String orderId, ServiceEvent serviceEvent) {
        this.orderId = orderId;
        this.serviceEvent = serviceEvent;
    }

    public MessageEvent(String orderId, ServiceEvent serviceEvent, IDataEvent dataEvent) {
        this.orderId = orderId;
        this.serviceEvent = serviceEvent;
        this.dataEvent = dataEvent;
    }

    public MessageEvent(String orderId, ServiceEvent serviceEvent, MessageStatusEvent statusEvent) {
        this.orderId = orderId;
        this.serviceEvent = serviceEvent;
        this.statusEvent = statusEvent;
    }

    public MessageEvent(String orderId, ServiceEvent serviceEvent, IDataEvent dataEvent, MessageStatusEvent statusEvent) {
        this.orderId = orderId;
        this.serviceEvent = serviceEvent;
        this.dataEvent = dataEvent;
        this.statusEvent = statusEvent;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setServiceEvent(ServiceEvent serviceEvent) {
        this.serviceEvent = serviceEvent;
    }

    public void setDataEvent(IDataEvent dataEvent) {
        this.dataEvent = dataEvent;
    }

    public void setStatusEvent(MessageStatusEvent statusEvent) {
        this.statusEvent = statusEvent;
    }

    public String getOrderId() {
        return orderId;
    }

    public ServiceEvent getServiceEvent() {
        return serviceEvent;
    }

    public IDataEvent getDataEvent() {
        return dataEvent;
    }

    public MessageStatusEvent getStatus() {
        return statusEvent;
    }
}
