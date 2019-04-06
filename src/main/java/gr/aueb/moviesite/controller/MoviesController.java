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

    /**
     * Serve Requests To /list By movies.html
     * @return Model And View Object
     */
    @GetMapping("list")
    public ModelAndView movies(){
        return new ModelAndView("movies.html");
    }
}
