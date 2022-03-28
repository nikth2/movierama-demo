package assignment.movies.demo.service;

import assignment.movies.demo.dto.MovieDto;
import assignment.movies.demo.model.MovieEntity;
import assignment.movies.demo.model.UserEntity;
import assignment.movies.demo.repository.MoviesRepository;
import assignment.movies.demo.repository.UserRepository;
import assignment.movies.demo.converters.MovieEntityToDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MoviesRepository moviesRepository;
    private final UserRepository userRepository;
    private final MovieEntityToDtoConverter movieEntityToDtoConverter;

    public List<MovieDto> getAllMovies() {
        return getAllMovies("publicationDate");
    }

    public List<MovieDto> getAllMovies(String sortType) {
        return moviesRepository.findAll(Sort.by(Sort.Direction.DESC, sortType)).stream()
                .map(movieEntity -> movieEntityToDtoConverter.convert(movieEntity))
                .collect(Collectors.toList());

    }

    public List<MovieDto> getAllMoviesForUser(String username) {
        return moviesRepository.findByUser(userRepository.findByUsername(username)).stream()
                .map(movieEntity -> movieEntityToDtoConverter.convert(movieEntity))
                .collect(Collectors.toList());
    }

    public void saveMovie(MovieDto movieDto) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setDescription(movieDto.getDescription());
            movieEntity.setTitle(movieDto.getTitle());
            movieEntity.setPublicationDate(LocalDateTime.now());
            movieEntity.setUser(userRepository.findByUsername(username));
            moviesRepository.save(movieEntity);
        }
    }
}
