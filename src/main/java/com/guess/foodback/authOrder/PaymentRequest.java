package com.guess.foodback.authOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class PaymentRequest  {

        private List<OrderItem> items;

        private  long totalPrice;

        private String name;

        private String address;

        private LatLng addressLatLng ;

        private Status status;

        private  Integer userId;

        private String paymentId;
}

