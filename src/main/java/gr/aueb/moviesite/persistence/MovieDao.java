package gr.aueb.moviesite.persistence;

import java.util.List;

/**
 * Movie DAO Interface
 * @author eskiada
 */
public interface MovieDao {

   boolean userExists(String email, String password);

   int insertUser(String name, String email, String password);

   int insertBookmark(String email, String movieId);

   List<String> getUserBookmarks(String email);
}
