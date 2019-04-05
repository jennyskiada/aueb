package gr.aueb.moviesite.model;

import lombok.Data;

/**
 * User Model Object
 * @author eskiada
 */
@Data
public class User {

    private Long Id;
    private String username;
    private String email;
    private String password;
}
