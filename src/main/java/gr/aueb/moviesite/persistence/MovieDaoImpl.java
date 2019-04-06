package gr.aueb.moviesite.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Movie DAO Implementation
 */
@Repository("GenericDao")
public class MovieDaoImpl implements MovieDao {

    private final ResourceBundle SQL_BUNDLE = ResourceBundle.getBundle("bundles/sql");

    @Autowired
    @SuppressWarnings("WeakerAccess")
    protected JdbcTemplate jdbcTemplate;

    /**
     * Retrieves A  SQL Query By It's Key From Corresponding Properties File
     * @param key The Key
     * @return The SQL Query
     */
    @SuppressWarnings("WeakerAccess")
    protected String getSQLQuery(String key){
        return SQL_BUNDLE.containsKey(key)? SQL_BUNDLE.getString(key) : null;
    }

    @Override
    public boolean userExists(String email, String password) {
        //log.info("userExists() Invoked For Email = {}.", email);
        int result = 0; // The Default Result
        try {
            result = jdbcTemplate.queryForObject(getSQLQuery("USER_EXISTS"), new Object[] { email, password }, Integer.class);
        } catch (Exception exception) {
            //log.error("Exception", exception);
        }
        return result > 0;
    }

    @Override
    public int insertUser(String name, String email, String password) {
        //log.info("insertUser() Invoked For Name = {}.", name);
        try {
            return jdbcTemplate.update(getSQLQuery("INSERT_USER"), name, email, password);
        } catch (DataAccessException exception) {
            //log.error("Exception", exception);
            return 0;
        }
    }

    @Override
    public int insertBookmark(String email, String movieId) {
        //log.info("insertBookmark() Invoked For Email = {}.", email);
        try {
            return jdbcTemplate.update(getSQLQuery("INSERT_BOOKMARK"), email, movieId);
        } catch (DataAccessException exception) {
            //log.error("Exception", exception);
            return 0;
        }
    }

    @Override
    public boolean bookmarkExists(String email, String movieId) {
        int result = 0; // The Default Result
        try {
            result = jdbcTemplate.queryForObject(getSQLQuery("BOOKMARK_EXISTS"), new Object[] { email, movieId }, Integer.class);
        } catch (Exception exception) {
            //log.error("Exception", exception);
        }
        return result > 0;
    }

    @Override
    public List<String> getUserBookmarks(String email) {
        //log.info("getUserBookmarks() Invoked For GUID = {}", email);
        List<String> result = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(getSQLQuery("GET_USER_BOOKMARKS"), email);
            for(Map<String, Object> row : rows) {
                if(row.get("MOVIE_ID")!=null) {
                    result.add((String) row.get("MOVIE_ID"));
                }
            }
        } catch (DataAccessException exception) {
            //log.error("Exception", exception);
        }
        return result;
    }

    @Override
    public String getUserName(String email) {
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(getSQLQuery("GET_USER_NAME"), new Object[] { email }, String.class);
        } catch (Exception exception) {
            //log.error("Exception", exception);
        }
        return result;
    }
}
