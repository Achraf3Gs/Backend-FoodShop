package com.guess.foodback.authOrder;

import com.guess.foodback.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "_order")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String address;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "address_latlng_id", nullable = false)
        private LatLng addressLatLng;

        @Column
        private String paymentId;

        @Column(nullable = false)
        private double totalPrice;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "order_id")
        private List<OrderItem> items;




        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;


        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date updatedAt;

        @Enumerated(EnumType.STRING)
        private Status status;




        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;





        public Integer getUserId() {
                return user != null ? user.getId() : null;
        }



}






