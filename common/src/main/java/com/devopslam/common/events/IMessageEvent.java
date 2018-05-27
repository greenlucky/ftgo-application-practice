package com.devopslam.common.events;

import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;

public interface IMessageEvent {
    String getOrderId();
    ServiceEvent getServiceEvent();
    IDataEvent getDataEvent();
    MessageStatusEvent getStatus();


}
