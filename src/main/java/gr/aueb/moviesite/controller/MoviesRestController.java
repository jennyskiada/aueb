package gr.aueb.moviesite.controller;

import gr.aueb.moviesite.model.Bookmark;
import gr.aueb.moviesite.model.GetBookmarksResponse;
import gr.aueb.moviesite.model.User;
import gr.aueb.moviesite.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Movies Controller Class
 * @author eskiada
 */
//@Slf4j
@RestController
public class MoviesRestController {

    @Autowired
    private MoviesService moviesService;

    /**
     * Used During Login To Check If The Credentials Exist
     * @param user User Object
     * @return true If User Exists, Otherwise false
     */
    @PostMapping(value = "userExists", produces = "application/json")
    public boolean userExists(@RequestBody User user) {
        //log.info("userExists() Endpoint Invoked For Email = {}.", user.getEmail());
        return moviesService.checkUserExistence(user.getEmail(), user.getPassword());
    }

    /**
     * Used During Registration To Create A User
     * @param user User Object
     */
    @PostMapping(value = "insertUser", produces = "application/json")
    public void insertUser(@RequestBody User user) {
        //log.info("insertUser() Endpoint Invoked For Email = {}.", user.getEmail());
        moviesService.insertUser(user);
    }







    @PostMapping(value = "login", produces = "application/json")
    public boolean login(@RequestBody User user) {
        //log.info("login() Endpoint Invoked For Email = {}.", user.getEmail());
        return moviesService.loginUser(user);
    }



    @PostMapping(value = "bookmark", produces = "application/json")
    public void insertBookmark(@RequestBody Bookmark bookmark) {
        //log.info("userExists() Endpoint Invoked For Email = {}.", bookmark.getEmail());
        moviesService.insertBookmark(bookmark.getEmail(), bookmark.getMovieId());
    }

    @GetMapping(value = "bookmarks", produces = "application/json")
    public GetBookmarksResponse getBookmarks(@PathVariable (value = "email") String email) {
        //log.info("getBookmarks() Endpoint Invoked For Email = {}.", email);
        return moviesService.getUserBookmarksByEmail(email);
    }
}
