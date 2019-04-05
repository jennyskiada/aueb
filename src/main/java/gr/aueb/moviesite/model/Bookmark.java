package gr.aueb.moviesite.model;

import lombok.Data;

/**
 * Bookmark Model Object
 * @author eskiada
 */
@Data
public class Bookmark{

    private Long id;
    private Long userId;
    private String email;
    private String movieId;
}
