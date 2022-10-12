package com.example.spring02.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private boolean admin = false;
}
