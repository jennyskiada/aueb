package gr.aueb.moviesite.persistence;

import gr.aueb.moviesite.model.Bookmark;

import java.util.List;

/**
 * Movie DAO Interface
 * @author eskiada
 */
public interface MovieDao {

   boolean userExists(String email);

   int insertUser(String name, String email, String password);

   int insertBookmark(Long userId, Long movieId);

   List<Bookmark> getUserBookmarks(String email);
}
