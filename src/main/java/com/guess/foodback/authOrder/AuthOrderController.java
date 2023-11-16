package com.guess.foodback.authOrder;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class AuthOrderController {

    private final OrderService service;
    private final OrderRepository orderRepository;


    public AuthOrderController(OrderService service,OrderRepository orderRepository) {

        this.service = service;
        this.orderRepository = orderRepository;

    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> register(@RequestBody RegisterOrderRequest request ){
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/newOrderForCurrentUser/{userId}")
    public Order getNewOrderForCurrentUser(@PathVariable("userId") Integer userId) {
        // Implement logic to retrieve a new order for the current user
        return service.findNewOrderForCurrentUser(userId);
    }
//
@PostMapping("/pay")
public ResponseEntity<OrderResponse>  payForOrder(@RequestBody PaymentRequest paymentRequest){
    return ResponseEntity.ok(service. payForOrder(paymentRequest));
}

    @GetMapping("track/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {

        Optional<Order> p =orderRepository.findById(Long.valueOf(orderId));
        return p.get();
    }



}











