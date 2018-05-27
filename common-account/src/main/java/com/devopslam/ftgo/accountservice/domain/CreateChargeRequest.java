package com.devopslam.ftgo.accountservice.domain;

import com.devopslam.common.events.IDataEvent;

public class CreateChargeRequest extends ChargeRequest implements IDataEvent {

    public CreateChargeRequest() {
    }

    public CreateChargeRequest(String description, int amount, Currency currency, String stripeEmail, String stripeToken) {
        super(description, amount, currency, stripeEmail, stripeToken);
    }
}
