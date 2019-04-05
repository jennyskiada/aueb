package gr.aueb.moviesite.service;

import gr.aueb.moviesite.model.GetBookmarksResponse;
import gr.aueb.moviesite.model.User;
import gr.aueb.moviesite.persistence.MovieDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        if(StringUtils.trimToNull(email) != null && StringUtils.trimToNull(password) != null) {
            return movieDao.userExists(email, password);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public void insertUser(User user) {
        if(StringUtils.trimToNull(user.getEmail()) != null && StringUtils.trimToNull(user.getPassword()) != null) {
            movieDao.insertUser(user.getName(), user.getEmail(), user.getPassword());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public void insertBookmark(final String email, final String movieId) {
        if(StringUtils.trimToNull(email) != null && StringUtils.trimToNull(movieId) != null) {
            movieDao.insertBookmark(email, movieId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetBookmarksResponse getUserBookmarksByEmail(final String email) {
        GetBookmarksResponse result = new GetBookmarksResponse();
        List bookmarks = new ArrayList();
        if(StringUtils.trimToNull(email) != null) {
            bookmarks = movieDao.getUserBookmarks(email);
        }
        result.setEmail(email);
        result.setBookmarks(bookmarks);
        return result;
    }
}
