package gr.aueb.moviesite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gr.aueb.moviesite.model.Bookmark;
import gr.aueb.moviesite.model.GetBookmarksResponse;
import gr.aueb.moviesite.model.User;
import gr.aueb.moviesite.service.MoviesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Movies Controller Class
 * @author eskiada
 */
@Slf4j
@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @PostMapping(value = "userExists", produces = "application/json")
    public boolean userExists(@RequestBody User user) {
        log.info("userExists() Endpoint Invoked For Email = {}.", user.getEmail());
        return moviesService.checkUserExistence(user.getEmail(), user.getPassword());
    }

    @PostMapping(value = "user", produces = "application/json")
    public void insertUser(@RequestBody User user) {
        log.info("insertUser() Endpoint Invoked For Email = {}.", user.getEmail());
        moviesService.insertUser(user);
    }

    @PostMapping(value = "bookmark", produces = "application/json")
    public void insertBookmark(@RequestBody Bookmark bookmark) {
        log.info("userExists() Endpoint Invoked For Email = {}.", bookmark.getEmail());
        moviesService.insertBookmark(bookmark.getEmail(), bookmark.getMovieId());
    }

    @GetMapping(value = "bookmarks", produces = "application/json")
    public GetBookmarksResponse getBookmarks(@PathVariable (value = "email") String email) {
        log.info("getBookmarks() Endpoint Invoked For Email = {}.", email);
        return moviesService.getUserBookmarksByEmail(email);
    }
}
