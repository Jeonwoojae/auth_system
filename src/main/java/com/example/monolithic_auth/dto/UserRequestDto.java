package com.example.monolithic_auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserRequestDto {

    private String email;
    private String password;

    public void toEntity(){
    }
}
