package gr.aueb.moviesite.persistence;

import gr.aueb.moviesite.model.Bookmark;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(final String email) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertUser(final String name, final String email, final String password) {
        log.info("insertUser() Invoked For Name = {}.", name);
        try {
            return jdbcTemplate.update(getSQLQuery("INSERT_USER"), new Object[] { name, email, password });
        } catch (DataAccessException exception) {
            log.error("Exception", exception);
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insertBookmark(final Long userId, final Long movieId) {
        log.info("insertBookmark() Invoked For User Id = {}.", userId);
        try {
            return jdbcTemplate.update(getSQLQuery("INSERT_BOOKMARK"), new Object[] { userId, movieId});
        } catch (DataAccessException exception) {
            log.error("Exception", exception);
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getUserBookmarks(final String email) {
        log.info("getUserBookmarks() Invoked For GUID = {}", email);
        List<String> result = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(getSQLQuery("GET_USER_BOOKMARKS"), email);
            for(Map<String, Object> row : rows) {
                if(row.get("MOVIE_ID")!=null) {
                    result.add((String) row.get("MOVIE_ID"));
                }
            }
        } catch (DataAccessException exception) {
            log.error("Exception", exception);
        }
        return result;
    }
}
