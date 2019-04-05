package gr.aueb.moviesite.service;


import gr.aueb.moviesite.model.GetBookmarksResponse;
import gr.aueb.moviesite.model.User;

/**
 * Movies Service Interface
 * @author npapadopoulos
 */
public interface MoviesService {

    boolean checkUserExistence(String email, String password);

    void insertUser(User user);

    void insertBookmark(String email, String movieId);

    GetBookmarksResponse getUserBookmarksByEmail(String email);

    boolean loginUser(User user);
}
