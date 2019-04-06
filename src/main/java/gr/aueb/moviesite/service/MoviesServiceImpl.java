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
 */
@Service
@Transactional
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public boolean insertUser(User user) {
        int affected = 0;
        if(StringUtils.trimToNull(user.getEmail()) != null && StringUtils.trimToNull(user.getPassword()) != null) {
            if(!userEmailExists(user.getEmail())) {
                affected = movieDao.insertUser(user.getName(), user.getEmail(), user.getPassword());
            }
        }
        return affected > 0;
    }

    @Override
    public boolean userExists(User user) {
        if(StringUtils.trimToNull(user.getEmail()) != null && StringUtils.trimToNull(user.getPassword()) != null) {
            return movieDao.userExists(user.getEmail(), user.getPassword());
        }
        return false;
    }

    @Override
    public boolean userEmailExists(String email) {
        if(StringUtils.trimToNull(email) != null) {
            return movieDao.userEmailExists(email);
        }
        return false;
    }

    @Override
    public boolean insertBookmark(Bookmark bookmark) {
        int affected = 0;
        if(StringUtils.trimToNull(bookmark.getEmail())!=null && StringUtils.trimToNull(bookmark.getMovieId())!=null) {
            if(!bookmarkExists(bookmark)) {
                affected = movieDao.insertBookmark(bookmark.getEmail(), bookmark.getMovieId());
            }
        }
        return affected > 0;
    }

    @Override
    public boolean bookmarkExists(Bookmark bookmark) {
        boolean result = false;
        if(StringUtils.trimToNull(bookmark.getEmail())!=null && StringUtils.trimToNull(bookmark.getMovieId())!=null) {
            result = movieDao.bookmarkExists(bookmark.getEmail(), bookmark.getMovieId());
        }
        return result;
    }

    @Override
    public UserBookmarks getUserBookmarksByEmail(String email) {
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
}
