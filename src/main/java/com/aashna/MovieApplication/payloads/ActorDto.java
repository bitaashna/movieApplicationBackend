package com.aashna.MovieApplication.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto implements Serializable {
    private static final long serialVersionUID = 4439114469417994353L;

    private Integer id;
    private String imgBase64;
    private String name;
    private String bio;

    private Date dateOfBirth;
    private String gender;

}
