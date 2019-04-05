package gr.aueb.moviesite.model;

import lombok.Data;

/**
 * User Model Object
 * @author eskiada
 */
@Data
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
}
