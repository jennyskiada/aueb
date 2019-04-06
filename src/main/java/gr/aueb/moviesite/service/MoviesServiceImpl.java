package gr.aueb.moviesite.service;

import gr.aueb.moviesite.model.Bookmark;
import gr.aueb.moviesite.model.UserBookmarks;
import gr.aueb.moviesite.model.User;
import gr.aueb.moviesite.persistence.MovieDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Movies Service Implementation
 * @author npapadopoulos, eskiada
 */
@Service
@Transactional
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
    public void insertUser(User user) {
        if(StringUtils.trimToNull(user.getEmail()) != null && StringUtils.trimToNull(user.getPassword()) != null) {
            movieDao.insertUser(user.getName(), user.getEmail(), user.getPassword());
        }
    }

    @Override
    public boolean insertBookmark(Bookmark bookmark) {
        boolean result = false;
        if(StringUtils.trimToNull(bookmark.getEmail())!=null && StringUtils.trimToNull(bookmark.getMovieId())!=null) {
            if(!bookmarkExists(bookmark)) {
                movieDao.insertBookmark(bookmark.getEmail(), bookmark.getMovieId());
                result = true; // Persisted
            }
        }
        return result;
    }

    @Override
    public boolean bookmarkExists(Bookmark bookmark) {
        boolean result = false;
        if(StringUtils.trimToNull(bookmark.getEmail())!=null && StringUtils.trimToNull(bookmark.getMovieId())!=null) {
            result = movieDao.bookmarkExists(bookmark.getEmail(), bookmark.getMovieId());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserBookmarks getUserBookmarksByEmail(final String email) {
        UserBookmarks result = new UserBookmarks();
        result.setEmail(email);
        // Get User's Name
        result.setName(movieDao.getUserName(email));
        // Get User's Bookmark
        List bookmarks = new ArrayList();
        if(StringUtils.trimToNull(email) != null) {
            bookmarks = movieDao.getUserBookmarks(email);
        }
        result.setBookmarks(bookmarks);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean loginUser(final User user) {
        if(StringUtils.trimToNull(user.getEmail()) != null && StringUtils.trimToNull(user.getPassword()) != null) {
            return movieDao.login(user.getEmail(), user.getPassword());
        }
        return false;
    }
}
