package com.aashna.MovieApplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDto implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
