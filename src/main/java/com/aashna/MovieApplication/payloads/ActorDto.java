package com.aashna.MovieApplication.payloads;

import com.aashna.MovieApplication.entities.Actor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ActorDto implements Serializable {
    private static final long serialVersionUID = 4439114469417994353L;

    private Integer id;
    private String imgBase64;
    private String name;

    private Date dateOfBirth;
    private String gender;

    public ActorDto(Actor actor) {
        if (actor.getImg() != null) {
            Blob posterBlob = actor.getImg();
            int blobLength = 0;
            try {
                blobLength = (int) posterBlob.length();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            byte[] posterBytes = new byte[0];
            try {
                posterBytes = posterBlob.getBytes(1, blobLength);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            this.imgBase64 = Base64.getEncoder().encodeToString(posterBytes);

        }
    }
}
