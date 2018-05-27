package com.devopslam.ftgo.orderservice.controllers;

import com.devopslam.ftgo.orderservice.domain.CreateOrderRequest;
import com.devopslam.ftgo.orderservice.domain.CreateOrderResponse;
import com.devopslam.ftgo.orderservice.domain.LineItem;
import com.devopslam.ftgo.orderservice.domain.OrderResponse;
import com.devopslam.ftgo.orderservice.events.OrderState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url;

    private  String orderId;

    @Before
    public void setUp() throws Exception {
        url = "http://localhost:" + port + "/v1/orders";
        String restaurantId =  "6fbc54d4-efaa-49f6-a643-0459fac7de75";;
        String consumerId = "";
        List<LineItem> lineItems = Arrays.asList(new LineItem("4ec4d104-6170-41b7-a720-b8aa550f66e4", 2));
        CreateOrderRequest request = new CreateOrderRequest(restaurantId, consumerId, lineItems);
        ResponseEntity<CreateOrderResponse> response = restTemplate.postForEntity(url, request, CreateOrderResponse.class);
        orderId = response.getBody().getOrderId();

    }

    @Test
    public void createOrder() {
        String restaurantId =  "6fbc54d4-efaa-49f6-a643-0459fac7de75";;
        String consumerId = "";
        List<LineItem> lineItems = Arrays.asList(new LineItem("ccaf8134-ea51-441b-ab7b-cccbf4c259d7", 10));
        CreateOrderRequest request = new CreateOrderRequest(restaurantId, consumerId, lineItems);
        ResponseEntity<CreateOrderResponse> response = restTemplate.postForEntity(url, request, CreateOrderResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(response.getBody());
    }

    @Test
    public void createOrder_WrongRestaurantId() {
        String restaurantId =  "6fbc54d4-efaa-49f6-a643-0459fac7de715";;
        String consumerId = "";
        List<LineItem> lineItems = Arrays.asList(new LineItem("4ec4d104-6170-41b7-a720-b8aa550f66e4", 2));
        CreateOrderRequest request = new CreateOrderRequest(restaurantId, consumerId, lineItems);
        ResponseEntity<CreateOrderResponse> response = restTemplate.postForEntity(url, request, CreateOrderResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        System.out.println(response.getBody());
    }

    @Test
    public void createOrder_WrongRestaurant_MenuId() {
        String restaurantId =  "6fbc54d4-efaa-49f6-a643-0459fac7de75";;
        String consumerId = "";
        List<LineItem> lineItems = Arrays.asList(new LineItem("4ec4d104-6170-41b7-a720-b8aa550f66e41", 2));
        CreateOrderRequest request = new CreateOrderRequest(restaurantId, consumerId, lineItems);
        ResponseEntity<CreateOrderResponse> response = restTemplate.postForEntity(url, request, CreateOrderResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        System.out.println(response.getBody());
    }

    @Test
    public void getOrder() {
        url += "/" + orderId;
        ResponseEntity<OrderResponse> respGetOrder = restTemplate.getForEntity(url, OrderResponse.class);
        assertEquals(HttpStatus.OK, respGetOrder.getStatusCode());
        assertEquals(respGetOrder.getBody().getOrderId(), orderId);
        System.out.println(respGetOrder.getBody().toString());


    }

    @Test
    public void cancelOrder() {
        url += "/" + orderId + "/cancel";
        ResponseEntity<OrderResponse> respGetOrder = restTemplate.getForEntity(url, OrderResponse.class);
        assertEquals(HttpStatus.OK, respGetOrder.getStatusCode());
        assertEquals(respGetOrder.getBody().getOrderId(), orderId);
        assertEquals(OrderState.CANCELLED.name(), respGetOrder.getBody().getState());
    }

    @Test
    public void revise() {
    }
}