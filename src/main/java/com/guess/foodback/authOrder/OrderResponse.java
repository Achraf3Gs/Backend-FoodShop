package com.guess.foodback.authOrder;


import com.guess.foodback.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String message;

    private Integer id;

    private List<OrderItem> items;

    private Double totalPrice;

    private String name;

    private String address;

    private LatLng addressLatLng;

    private String paymentId;

    private Date createdAt;

    private Date updatedAt;

    private Status status;

    private User user;
}