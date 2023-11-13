package com.guess.foodback.authOrder;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class AuthOrderController {

    private final OrderService service;

    public AuthOrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> register(@RequestBody RegisterOrderRequest request ){
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/newOrderForCurrentUser/{userId}")
    public Order getNewOrderForCurrentUser(@PathVariable("userId") Long userId) {
        // Implement logic to retrieve a new order for the current user
        return service.findNewOrderForCurrentUser(userId);
    }











}
