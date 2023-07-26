package com.aashna.MovieApplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserSignInDto implements Serializable {
    private String email;
    private String password;
}