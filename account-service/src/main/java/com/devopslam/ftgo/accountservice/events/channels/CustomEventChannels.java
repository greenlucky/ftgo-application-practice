package com.devopslam.ftgo.accountservice.events.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomEventChannels {

    String ORDER_CREATE_ORDER_IN_CHANEl = "orderCreateIn";
    String ORDER_CREATE_ORDER_OUT_CHANEl = "orderCreateOut";

    @Input(CustomEventChannels.ORDER_CREATE_ORDER_IN_CHANEl)
    SubscribableChannel createOrderInput();

    @Output(CustomEventChannels.ORDER_CREATE_ORDER_OUT_CHANEl)
    MessageChannel orderCreateOur();
}
