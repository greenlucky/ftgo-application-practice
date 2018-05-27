package com.devopslam.ftgo.restaurantservice.repository;

import com.devopslam.ftgo.restaurantservice.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    Optional<Restaurant> findById(String id);
}
