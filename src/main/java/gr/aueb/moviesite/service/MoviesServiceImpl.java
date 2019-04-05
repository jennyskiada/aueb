package gr.aueb.moviesite.service;

import gr.aueb.moviesite.persistence.MovieDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Movies Service Implementation
 * @author npapadopoulos, eskiada
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MovieDao movieDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserExistence(final String email, final String password) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertUser(final String name, final String email, final String password) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertBookmark(final Long userId, final Long movieId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getUserBookmarksByEmail(final String email) {
        return null;
    }
}
