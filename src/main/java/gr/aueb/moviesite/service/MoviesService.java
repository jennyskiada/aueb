package gr.aueb.moviesite.service;


import gr.aueb.moviesite.model.Bookmark;
import gr.aueb.moviesite.model.UserBookmarks;
import gr.aueb.moviesite.model.User;

/**
 * Movies Service Interface
 * @author npapadopoulos
 */
public interface MoviesService {

    boolean checkUserExistence(String email, String password);

    void insertUser(User user);

    boolean insertBookmark(Bookmark bookmark);

    boolean bookmarkExists(Bookmark bookmark);

    UserBookmarks getUserBookmarksByEmail(String email);

    boolean loginUser(User user);
}
