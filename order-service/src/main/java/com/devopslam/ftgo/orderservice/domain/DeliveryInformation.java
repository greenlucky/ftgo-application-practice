package com.devopslam.ftgo.orderservice.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class DeliveryInformation {

    private Long deliveryTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "state", column = @Column(name = "delivery_state"))
    })
    private Address deliveryAddress;

}
