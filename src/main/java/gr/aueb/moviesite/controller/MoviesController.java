package gr.aueb.moviesite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Movies Controller Class
 */
@Slf4j
@Controller
public class MoviesController {

    @GetMapping("movies/list")
    public ModelAndView getMoviesPage(){
        ModelAndView modelAndView = new ModelAndView("movies.html");
        return modelAndView;
    }
}
