package com.devopslam.ftgo.orderservice.events;

public class PaymentInformation {

    private String paymentToken;

    public PaymentInformation() {
    }

    public PaymentInformation(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }
}
