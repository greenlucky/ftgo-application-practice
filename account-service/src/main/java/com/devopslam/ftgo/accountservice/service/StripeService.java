package com.devopslam.ftgo.accountservice.service;

import com.devopslam.ftgo.accountservice.domain.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest request)
            throws CardException, APIException, AuthenticationException,
            InvalidRequestException, APIConnectionException {
        Map<String, Object> chargeParams = new HashMap<>();

        chargeParams.put("amount", request.getAmount());
        chargeParams.put("currency", request.getCurrency());
        chargeParams.put("description", request.getDescription());
        chargeParams.put("source", request.getStripeToken());

        return Charge.create(chargeParams);
    }

    public boolean validatePaymentInformation(ChargeRequest request) {
        return false;
    }
}
