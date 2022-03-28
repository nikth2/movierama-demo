package assignment.movies.demo.service;

import assignment.movies.demo.dto.UserDto;
import assignment.movies.demo.repository.MoviesRepository;
import assignment.movies.demo.repository.UserRepository;
import assignment.movies.demo.model.MovieEntity;
import assignment.movies.demo.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MoviesRepository moviesRepository;

    public UserEntity registerNewUserAccount(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setInsertDate(new Date());
        return userRepository.save(user);
    }

    public void rateMovie(Long movieId, boolean like) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<MovieEntity> movieOptional = moviesRepository.findById(movieId);
            if (movieOptional.isPresent()) {
                UserEntity user = userRepository.findByUsername(username);
                if (like) {
                    user.getLikedMovies().add(movieOptional.get());
                    user.setHatedMovies(remove(user.getHatedMovies(), movieId));
                } else {
                    user.getHatedMovies().add(movieOptional.get());
                    user.setLikedMovies(remove(user.getLikedMovies(), movieId));
                }
                userRepository.save(user);
            }
        }
    }

    private Set<MovieEntity> remove(Set<MovieEntity> movies, Long movieId) {
        return movies.stream()
                .filter(movie -> !movie.getId().equals(movieId))
                .collect(Collectors.toSet());
    }
}
