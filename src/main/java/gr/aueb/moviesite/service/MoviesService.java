package gr.aueb.moviesite.service;

import gr.aueb.moviesite.model.Bookmark;
import gr.aueb.moviesite.model.UserBookmarks;
import gr.aueb.moviesite.model.User;

/**
 * Movies Service Interface
 */
public interface MoviesService {

    boolean insertUser(User user);

    boolean userExists(User user);

    boolean userEmailExists(String email);

    boolean insertBookmark(Bookmark bookmark);

    boolean bookmarkExists(Bookmark bookmark);

    UserBookmarks getUserBookmarksByEmail(String email);
}
