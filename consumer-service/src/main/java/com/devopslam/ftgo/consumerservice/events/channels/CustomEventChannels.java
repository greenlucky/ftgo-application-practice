package com.devopslam.ftgo.consumerservice.events.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomEventChannels {

    String ORDER_CREATE_IN_CHANNEL = "orderCreateIn";
    String ORDER_CREATE_OUT_CHANNEL = "orderCreateOut";

    @Input(CustomEventChannels.ORDER_CREATE_IN_CHANNEL)
    SubscribableChannel orderCreateIn();

    @Output(CustomEventChannels.ORDER_CREATE_OUT_CHANNEL)
    MessageChannel orderCreateOut();

}
