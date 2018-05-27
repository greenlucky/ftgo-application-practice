package com.devopslam.ftgo.restaurantservice.events.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomEventChannels {

    String ORDER_CREATE_IN_CHANNEL = "orderCreateIn";
    String ORDER_CREATE_OUT_CHANNEL = "orderCreateOut";
    String ORDER_RES_CHANNEL = "orderResCreateOut";
    String RES_CHANGE_CHANNEL = "resChangeOut";

    @Input(CustomEventChannels.ORDER_CREATE_IN_CHANNEL)
    SubscribableChannel orderCreateIn();
    
    @Input(CustomEventChannels.ORDER_RES_CHANNEL)
    SubscribableChannel orderResCreateOut();

    @Output(CustomEventChannels.ORDER_CREATE_OUT_CHANNEL)
    MessageChannel orderCreateOut();

    @Output(CustomEventChannels.RES_CHANGE_CHANNEL)
    MessageChannel resChangeOut();

}
