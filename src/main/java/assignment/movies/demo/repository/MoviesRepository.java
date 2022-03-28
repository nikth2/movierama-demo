package assignment.movies.demo.repository;

import assignment.movies.demo.model.MovieEntity;
import assignment.movies.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByUser(UserEntity user);
}
