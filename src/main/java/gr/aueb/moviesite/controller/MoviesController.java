package gr.aueb.moviesite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Movies Controller Class
 */
@Controller
public class MoviesController {

    /**
     * Serve Home Page Requests
     * @return Model And View Object
     */
    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * Serve Requests To /bookmarks By bookmarks.html
     * @return Model And View Object
     */
    @GetMapping("bookmarks")
    public ModelAndView bookmarks(){
        return new ModelAndView("bookmarks");
    }

    /**
     * Serve Requests To /search By search.html
     * @return Model And View Object
     */
    @GetMapping("search")
    public ModelAndView search(){
        return new ModelAndView("search");
    }
}
