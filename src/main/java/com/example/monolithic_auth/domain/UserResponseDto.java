package com.example.monolithic_auth.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserResponseDto {

    private Long userId;
    private String email;
    private String password;
    private String name;
    private Role role;

    public UserResponseDto(Users users){
        this.userId = users.getId();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.name = users.getName();
        this.role = users.getRole();
    }
}
