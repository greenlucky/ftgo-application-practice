package com.devopslam.ftgo.orderservice.repository.redis;

import com.devopslam.ftgo.orderservice.domain.RestaurantOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDRestaurantOrderRepository extends CrudRepository<RestaurantOrder, String> {
}
