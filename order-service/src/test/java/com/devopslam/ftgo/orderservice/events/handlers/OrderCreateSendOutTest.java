package com.devopslam.ftgo.orderservice.events.handlers;

import com.devopslam.common.domain.Money;
import com.devopslam.ftgo.orderservice.domain.LineItem;
import com.devopslam.ftgo.orderservice.events.OrderCreateEvent;
import com.devopslam.ftgo.orderservice.events.channels.CustomEventChannels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderCreateSendOutTest {

    @Autowired
    private CustomEventChannels customEventChannels;

    @Autowired
    private OrderCreateSendOut sendOut;

    @Autowired
    private MessageCollector collector;

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
    public void publishOrderCreate() throws InterruptedException {
        String orderId = UUID.randomUUID().toString();

        OrderCreateEvent event = new OrderCreateEvent(orderId, restaurantId, customerId, null, new Money(20));
        sendOut.publishOrderCreate(event);
        BlockingQueue<Message<?>> messages = collector.forChannel(customEventChannels.orderCreateOut());

        assertThat(messages, receivesPayloadThat(is(event)));
        Thread.sleep(5000);
    }
}