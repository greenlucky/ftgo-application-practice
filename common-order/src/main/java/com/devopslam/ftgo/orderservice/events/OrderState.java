package com.devopslam.ftgo.orderservice.events;

public enum OrderState {
    CREATING_PENDING,
    AUTHORIZED,
    REJECTED,
    CANCEL_PENDING,
    CANCELED,
    REVISION_PENDING
}
