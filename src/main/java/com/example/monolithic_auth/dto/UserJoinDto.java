package com.example.monolithic_auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
public class UserJoinDto {
    private String email;
    private String password;
    private String name;
}
