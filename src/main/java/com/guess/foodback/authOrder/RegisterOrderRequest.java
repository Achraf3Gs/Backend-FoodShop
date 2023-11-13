package com.guess.foodback.authOrder;


import com.guess.foodback.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrderRequest {

    private  List<OrderItem> items;

    private  long totalPrice;

    private String name;

    private String address;

    private LatLng addressLatLng ;

    private Status status;

    private  Integer userIdt;







}
