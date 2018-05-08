package com.devopslam.ftgo.restaurantservice.repository;

import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
