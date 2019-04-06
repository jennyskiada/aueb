package gr.aueb.moviesite.persistence;

import java.util.List;

/**
 * Movie DAO Interface
 */
public interface MovieDao {

   boolean userExists(String email, String password);

   boolean userEmailExists(String email);

   int insertUser(String name, String email, String password);

   int insertBookmark(String email, String movieId);

   boolean bookmarkExists(String email, String movieId);

   List<String> getUserBookmarks(String email);

   String getUserName(String email);
}
