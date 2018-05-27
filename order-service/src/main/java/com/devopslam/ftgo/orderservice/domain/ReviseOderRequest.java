package com.devopslam.ftgo.orderservice.domain;

import java.util.Map;

public class ReviseOderRequest {

    private Map<String, String> revisedLineItemQuantities;

    public ReviseOderRequest() {
    }

    public ReviseOderRequest(Map<String, String> revisedLineItemQuantities) {
        this.revisedLineItemQuantities = revisedLineItemQuantities;
    }

    public Map<String, String> getRevisedLineItemQuantities() {
        return revisedLineItemQuantities;
    }

    public void setRevisedLineItemQuantities(Map<String, String> revisedLineItemQuantities) {
        this.revisedLineItemQuantities = revisedLineItemQuantities;
    }
}
