package com.guess.foodback.authOrder;

import com.guess.foodback.entities.User;
import com.guess.foodback.entities.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;


    public OrderResponse register(RegisterOrderRequest request) {

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        LocalDateTime ldt= LocalDateTime.now();
        DateTimeFormatter format =DateTimeFormatter.ofPattern("yyyy_MM_mm_hhmmss");
        String var= ldt.format(format);



        Integer userId = request.getUserIdt();
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found")); // Assuming you have a UserRepository

        Order existingOrder = repository.findFirstByUser_IdAndStatus(userId, Status.NEW);

        if (existingOrder != null) {
            // If an existing order is found, delete it
            repository.delete(existingOrder);
        }


        var order= Order.builder()
                .items(request.getItems())
                .totalPrice(request.getTotalPrice())
                .name(request.getName())
                .address(request.getAddress())
                .addressLatLng(request.getAddressLatLng())
                .createdAt(var)
                .updatedAt(var)
                .status(Status.NEW)
                .user(user)
                .build();
        repository.save(order);

        String address = order.getAddress();
        LatLng addressLatLng = order.getAddressLatLng();
        String createdAt= order.getCreatedAt();
        Integer id = Math.toIntExact(order.getId());
        List<OrderItem> items = order.getItems();
        String name = order.getName();
        Status status= order.getStatus();
        Double totalPrice= order.getTotalPrice();
        String updatedAt= order.getUpdatedAt();


        return OrderResponse.builder()
                .address(address)
                .addressLatLng(addressLatLng)
                .createdAt(createdAt)
                .id(id)
                .items(items)
                .name(name)
                .status(status)
                .totalPrice(totalPrice)
                .updatedAt(updatedAt)
                .user(user)

                .build();

    }


    public Order findNewOrderForCurrentUser(Integer userId) {
        // Implement logic to find a new order for the current user
        return repository.findFirstByUser_IdAndStatus(userId, Status.NEW);
    }


    public OrderResponse payForOrder(@RequestBody PaymentRequest request) {

        String paymentId = request.getPaymentId();
        Integer userId = request.getUserId();
        Order order = findNewOrderForCurrentUser(userId);
        if (order == null) {
            String message = "Order Not Found!";
            return OrderResponse.builder()
                    .message(message)

                    .build();
        }


        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found")); // Assuming you have a UserRepository

        order.setPaymentId(paymentId);
        order.setStatus(Status.PAYED);
        // Assuming you have a method like saveOrder in your service class to save the changes.
        repository.save(order);

        Integer order_id = Math.toIntExact(order.getId());
        String address = order.getAddress();
        LatLng addressLatLng = order.getAddressLatLng();
        String createdAt = order.getCreatedAt();

        List<OrderItem> items = order.getItems();
        String name = order.getName();
        Status status = order.getStatus();
        Double totalPrice = order.getTotalPrice();
        String updatedAt = order.getUpdatedAt();
        String message = "Payment Saved Successfully";


        return OrderResponse.builder()
                .message(message)
                .address(address)
                .addressLatLng(addressLatLng)
                .createdAt(createdAt)
                .id(order_id)
                .items(items)
                .name(name)
                .status(status)
                .totalPrice(totalPrice)
                .updatedAt(updatedAt)
                .user(user)
                .paymentId(paymentId)
                .build();

    }


}