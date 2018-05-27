package com.devopslam.ftgo.restaurantservice.repository;

import com.devopslam.ftgo.restaurantservice.domain.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, String> {
}
