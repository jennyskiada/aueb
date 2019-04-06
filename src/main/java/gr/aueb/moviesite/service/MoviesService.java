package gr.aueb.moviesite.service;

import gr.aueb.moviesite.model.Bookmark;
import gr.aueb.moviesite.model.UserBookmarks;
import gr.aueb.moviesite.model.User;

/**
 * Movies Service Interface
 */
public interface MoviesService {

    boolean checkUserExistence(User user);

    void insertUser(User user);

    boolean insertBookmark(Bookmark bookmark);

    boolean bookmarkExists(Bookmark bookmark);

    UserBookmarks getUserBookmarksByEmail(String email);
}
