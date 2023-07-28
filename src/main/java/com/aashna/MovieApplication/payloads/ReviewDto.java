package com.aashna.MovieApplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto implements Serializable {

    private Integer id;
    private String content;
    private Integer rating;
    private Date addedDate;
    private String firstName;
    private String lastName;



}

