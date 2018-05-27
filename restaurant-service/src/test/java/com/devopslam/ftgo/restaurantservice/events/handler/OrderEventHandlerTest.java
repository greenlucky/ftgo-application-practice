package com.devopslam.ftgo.restaurantservice.events.handler;

import com.devopslam.common.domain.Money;
import com.devopslam.ftgo.restaurantservice.BaseTest;
import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.events.CreateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.MenuItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantMessage;
import com.devopslam.ftgo.restaurantservice.service.RestaurantService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

public class OrderEventHandlerTest extends BaseTest {

    @Autowired
    private Sink sink;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private Source source;

    @Autowired
    private MessageCollector collector;

    @Test
    public void get_restaurants_message() {
        RestaurantMessage message = new RestaurantMessage("12345");
        sink.input().send(new GenericMessage<>(message));
    }

    @Test
    public void send_restaurant() {
        String id = "6fbc54d4-efaa-49f6-a643-0459fac7de75";
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(UUID.randomUUID().toString(), "Fish with price", new Money(2)),
                new MenuItem(UUID.randomUUID().toString(), "Fish with eggs", new Money("2.2")),
                new MenuItem(UUID.randomUUID().toString(), "Fish with tomato", new Money(1))
        );
        CreateRestaurantRequest restaurant = new CreateRestaurantRequest("MNU", menuItems);
        Restaurant result = restaurantService.create(restaurant);
        sink.input().send(new GenericMessage<>(new RestaurantMessage(result.getId())));
        BlockingQueue<Message<?>> messages = collector.forChannel(source.output());

        assertThat(messages, receivesPayloadThat(is(result)));
    }
}