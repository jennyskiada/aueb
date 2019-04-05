package gr.aueb.moviesite.service;


import java.util.List;

/**
 * Movies Service Interface
 * @author npapadopoulos
 */
public interface MoviesService {

    boolean checkUserExistence(String email, String password);

    void insertUser(String name, String email, String password);

    void insertBookmark(Long userId, Long movieId);

    List<String> getUserBookmarksByEmail(String email);
}
