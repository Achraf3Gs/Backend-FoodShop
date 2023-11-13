package com.guess.foodback.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "CookTime is mandatory")
    @Column(name = "cookTime")
    private String cookTime;


    @Column(name = "price")
    private double price;

    @Column(name = "favorite")
    private boolean favorite;

    @Column(name = "origins")
    private List<String> origins;

    @Column(name = "stras")
    private double stars;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "tags")
    private List<String> tags;

    public boolean getFavorite() {
        return favorite;
    }
}

