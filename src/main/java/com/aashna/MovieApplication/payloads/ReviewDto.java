package com.aashna.MovieApplication.payloads;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ReviewDto implements Serializable {

    private Integer id;
    private String content;
    private String firstName;
    private String lastName;


}

