package com.devopslam.ftgo.consumerservice.domain;

public class CreateConsumerResponse {

    private String id;

    public CreateConsumerResponse(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateConsumerResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
