package com.guess.foodback.authOrder;

import com.guess.foodback.entities.Food;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "order_items")
    public class OrderItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "food_id", nullable = false)
        private Food food;


        @Column(nullable = false)
        private double price;

        @Column(nullable = false)
        private int quantity;


    }

