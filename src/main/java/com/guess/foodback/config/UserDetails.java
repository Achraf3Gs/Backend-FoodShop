package com.guess.foodback.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails  {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;

    public UserDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }








}
















