package assignment.movies.demo.controller;

import assignment.movies.demo.dto.MovieDto;
import assignment.movies.demo.dto.UserDto;
import assignment.movies.demo.model.UserEntity;
import assignment.movies.demo.service.MovieService;
import assignment.movies.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MovieService movieService;
    private final UserService userService;

    @RequestMapping(value = "/list")
    public String list(ModelMap model, @RequestParam(required = false) String username) {
        if (StringUtils.trimToNull(username) != null) {
            model.addAttribute("movies", movieService.getAllMoviesForUser(username));
        } else {
            model.addAttribute("movies", movieService.getAllMovies());
        }
        return "movies";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public String sort(ModelMap model, @RequestParam(defaultValue = "publicationDate") String type) {
        model.addAttribute("movies",movieService.getAllMovies(type));
        return "movies";
    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public String rate(ModelMap model, @RequestParam Long movieId, @RequestParam Boolean like) {
        userService.rateMovie(movieId, like);
        model.addAttribute("movies",movieService.getAllMovies());
        return "movies";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMovie(ModelMap model) {
        MovieDto movieDto = new MovieDto();
        model.addAttribute("movieDto",movieDto);
        return "new_movie";
    }

    @PostMapping("/newMovie")
    public String registerUserAccount(@Valid MovieDto movieDto,
                                      BindingResult result,
                                      ModelMap model,
                                      HttpServletRequest request) throws ServletException {
        if (result.hasErrors()) {
            model.addAttribute("movieDto", movieDto);
            return "new_movie";
        }
        movieService.saveMovie(movieDto);
        return "redirect:/";
    }

}
