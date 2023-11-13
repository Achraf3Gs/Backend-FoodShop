package com.guess.foodback.authOrder;


import com.guess.foodback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findFirstByUser_IdAndStatus(Integer userId, Status status);

}


