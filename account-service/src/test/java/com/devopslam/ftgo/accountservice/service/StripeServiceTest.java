package com.devopslam.ftgo.accountservice.service;

import com.devopslam.ftgo.accountservice.domain.ChargeRequest;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StripeServiceTest {

    @Autowired
    private StripeService stripeService;

    @Test
    public void charge() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        String desc = "Test checkout stripe";
        int amount = 1 * 100;
        String stripeEmail = "nguyenlamit86@gmail.com";
        String stripeToken = "tok_mastercard";
        ChargeRequest request = new ChargeRequest(desc, amount, ChargeRequest.Currency.USD, stripeEmail, stripeToken);

        Charge charge = stripeService.charge(request);
        System.out.println(charge.toJson());
    }
}