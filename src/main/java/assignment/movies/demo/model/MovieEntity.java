package assignment.movies.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    @Column(name="publication_date",nullable = false)
    private LocalDateTime publicationDate;
    @ManyToMany(mappedBy = "likedMovies")
    private Set<UserEntity> likes;
    @ManyToMany(mappedBy = "hatedMovies")
    private Set<UserEntity> hates;
}
