package gr.aueb.moviesite.model;

import lombok.Data;

/**
 * User Model Object
 * @author eskiada
 */
@Data
public class User {

    private Long Id;
    private String name;
    private String email;
    private String password;
}
