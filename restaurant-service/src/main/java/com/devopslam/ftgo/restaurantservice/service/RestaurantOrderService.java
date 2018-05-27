package com.devopslam.ftgo.restaurantservice.service;

import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import com.devopslam.ftgo.restaurantservice.domain.RestaurantOrder;
import com.devopslam.ftgo.restaurantservice.events.RestaurantLineItem;
import com.devopslam.ftgo.restaurantservice.events.RestaurantOrderState;
import com.devopslam.ftgo.restaurantservice.repository.RestaurantOrderRepository;
import com.devopslam.ftgo.restaurantservice.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RestaurantOrderService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantOrderService.class);

    @Autowired
    private RestaurantOrderRepository resOrderRepository;

    @Autowired
    private RestaurantRepository resRepository;

    @Transactional
    public RestaurantOrder createRestaurantOrder(String restaurantId,
                                                 String restaurantOrderId,
                                                 List<RestaurantLineItem> lineItems) {
        Optional<Restaurant> restaurant = Optional.ofNullable(resRepository.getOne(restaurantId));

        if (!restaurant.isPresent()) throw new RuntimeException("Not found restaurant Id: " + restaurantId);

        //check exist restaurant menu id
        checkRestaurantLineItem(restaurant.get(), lineItems);

        RestaurantOrder order = new RestaurantOrder(restaurantOrderId, restaurantId, lineItems);
        resOrderRepository.save(order);
        return order;
    }

    private void checkRestaurantLineItem(Restaurant restaurant, List<RestaurantLineItem> lineItems) {
        lineItems.stream().forEach(lineItem -> {
            restaurant.getMenuItems().stream()
                    .filter(menuItem -> menuItem.getId().equals(lineItem.getMenuItemId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Not found Restaurant Menu Item Id: " + lineItem.getMenuItemId()));
        });
    }


    @Transactional
    public void changeStateResOrder(String restaurantOrderId, RestaurantOrderState state) {
        Optional<RestaurantOrder> restaurantOrder = Optional.ofNullable(resOrderRepository.getOne(restaurantOrderId));

        if (!restaurantOrder.isPresent())
            throw new RuntimeException("Not found restaurant order Id: " + restaurantOrderId);
        restaurantOrder.get().setPreviousState(restaurantOrder.get().getState());
        restaurantOrder.get().setState(state);
        resOrderRepository.save(restaurantOrder.get());
        logger.info("Updated stage from {} to {} Restaurant Order Id: {}",
                restaurantOrder.get().getPreviousState(), state, restaurantOrderId);
    }

}
