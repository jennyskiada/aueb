package gr.aueb.moviesite.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ResourceBundle;

/**
 * Movie DAO Implementation
 * @author eskiada
 */
@Repository("GenericDao")
@SuppressWarnings("unused")
public class MovieDaoImpl implements MovieDao {

    private final ResourceBundle SQL_BUNDLE = ResourceBundle.getBundle("bundles/sql");

    @Autowired
    @SuppressWarnings("WeakerAccess")
    protected JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer probeDatabase() {
        return jdbcTemplate.queryForObject(getSQLQuery("PROBE"), Integer.class);
    }

    /**
     * Retrieves A  SQL Query By It's Key From Corresponding Properties File
     * @param key The Key
     * @return The SQL Query
     */
    @SuppressWarnings("WeakerAccess")
    protected String getSQLQuery(String key){
        return SQL_BUNDLE.containsKey(key)? SQL_BUNDLE.getString(key) : null;
    }
}
