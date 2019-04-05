package gr.aueb.moviesite.model;

import lombok.Data;

import java.util.List;

/**
 * GetBookmarksResponse Model Object
 * @author eskiada
 */
@Data
public class GetBookmarksResponse {

    private String email;
    private List<String> bookmarks;
}
