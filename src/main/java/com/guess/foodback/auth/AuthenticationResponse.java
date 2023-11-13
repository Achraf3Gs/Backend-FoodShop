package com.guess.foodback.auth;


import com.guess.foodback.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String message;

    private String token;

    private String name;

    private String address;

    private Role role;

    private Integer id;


}
