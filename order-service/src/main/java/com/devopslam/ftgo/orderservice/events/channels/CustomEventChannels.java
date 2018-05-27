package com.devopslam.ftgo.orderservice.events.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface CustomEventChannels {

    String ORDER_CREATE_OUT_CHANNEL = "orderCreateOut";
    String ORDER_CREATE_IN_CHANNEL = "orderCreateIn";
    String ORDER_RES_CHANNEL = "orderResCreateIn";
    String RES_CHANGE_CHANNEL = "resChangeIn";


    @Output(CustomEventChannels.ORDER_CREATE_OUT_CHANNEL)
    MessageChannel orderCreateOut();

    @Input(CustomEventChannels.ORDER_CREATE_IN_CHANNEL)
    SubscribableChannel orderCreateIn();

    @Output(CustomEventChannels.ORDER_RES_CHANNEL)
    MessageChannel orderResCreateIn();

    @Input(CustomEventChannels.RES_CHANGE_CHANNEL)
    SubscribableChannel resChangeIn();
    
}
