package gr.aueb.moviesite.persistence;

import java.util.List;

/**
 * Movie DAO Interface
 * @author eskiada
 */
public interface MovieDao {

   boolean userExists(String email, String password);

   int insertUser(String name, String email, String password);

   int insertBookmark(Long userId, Long movieId);

   List<String> getUserBookmarks(String email);
}
