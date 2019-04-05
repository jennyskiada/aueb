package gr.aueb.moviesite.service;

import gr.aueb.moviesite.persistence.MovieDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Movies Service Implementation
 * @author npapadopoulos, eskiada
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class MoviesServiceImpl implements MoviesService {

    @Value("${movie.api.url}")
    private String apiUrl;
    @Value("${movie.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieDao movieDao;
}
