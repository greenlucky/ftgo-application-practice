package com.devopslam.ftgo.consumerservice.domain;

import com.devopslam.common.domain.PersonName;

public class CreateConsumerRequest {

    private PersonName personName;

    public CreateConsumerRequest() {
    }

    public CreateConsumerRequest(PersonName personName) {
        this.personName = personName;
    }

    public PersonName getName() {
        return personName;
    }
}
