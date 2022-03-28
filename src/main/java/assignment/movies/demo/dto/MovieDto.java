package assignment.movies.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MovieDto {
    private Long id;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String description;
    private String username;
    private LocalDateTime publicationDate;
    private String publicationDateDifference;
    private Integer likes;
    private Integer hates;
    private Boolean likedByUser;
    private Boolean hatedByUser;
}
