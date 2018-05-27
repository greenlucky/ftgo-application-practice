package com.devopslam.ftgo.orderservice.repository.redis;

import com.devopslam.ftgo.orderservice.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDRestaurantRepository extends CrudRepository<Restaurant, String> {
}
