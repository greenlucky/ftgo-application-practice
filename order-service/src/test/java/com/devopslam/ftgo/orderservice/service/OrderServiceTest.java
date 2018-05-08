package com.devopslam.ftgo.orderservice.service;

import com.devopslam.ftgo.orderservice.domain.LineItem;
import com.devopslam.ftgo.orderservice.domain.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    private String customerId;
    private String restaurantId;
    private List<LineItem> lineItems;

    @Before
    public void init() {
        lineItems = Arrays.asList(
            new LineItem("123456", 2),
            new LineItem("123456", 2),
            new LineItem("123456", 2),
            new LineItem("123456", 2)
        );
        customerId = "123456";
        restaurantId = "123456";
    }

    @Test
    public void create_order() throws Exception {
        Order order = orderService.createOrder(customerId, restaurantId, lineItems);
        System.out.println(order.toString());
    }

}