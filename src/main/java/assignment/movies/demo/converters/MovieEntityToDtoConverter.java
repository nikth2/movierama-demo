package assignment.movies.demo.converters;

import assignment.movies.demo.dto.MovieDto;
import assignment.movies.demo.model.MovieEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Component
public class MovieEntityToDtoConverter {
    public MovieDto convert(MovieEntity entity) {
        MovieDto dto = new MovieDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublicationDate(entity.getPublicationDate());
        dto.setPublicationDateDifference(getDateDifference(entity));
        dto.setHates(entity.getHates().size());
        dto.setLikes(entity.getLikes().size());
        dto.setUsername(entity.getUser().getUsername());
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            dto.setLikedByUser(entity.getLikes().stream()
                    .anyMatch(userEntity -> userEntity.getUsername().equals(username)));
            dto.setHatedByUser(entity.getHates().stream()
                    .anyMatch(userEntity -> userEntity.getUsername().equals(username)));
        }
        return dto;
    }

    private String getDateDifference(MovieEntity entity) {
        Period p = Period.between(LocalDate.now(), entity.getPublicationDate().toLocalDate());
        Duration duration = Duration.between(LocalDateTime.now(), entity.getPublicationDate());
        String diff ="";
        diff += getMinutes(duration);
        diff += getHours(duration);
        diff += getDays(p);
        diff += getMonths(p);
        diff += getYears(p);
        diff += diff.trim().length() > 0 ? "ago" : "";
        return diff;
    }

    private String getDays(Period period) {
        String out = "";
        int days = Math.abs(period.getDays());
        out = days == 1 ? days + " day " : out;
        out = days > 1 ? days + " days " : out;
        return out;
    }

    private String getMonths(Period period) {
        String out = "";
        int months = Math.abs(period.getMonths());
        out = months == 1 ? months + " month " : out;
        out = months > 1 ? months + " months " : out;
        return out;
    }

    private String getYears(Period period) {
        String out = "";
        int years = Math.abs(period.getYears());
        out = years == 1 ? years + " year " : out;
        out = years > 1 ? years + " years " : out;
        return out;
    }

    private String getMinutes(Duration duration) {
        String out = "";
        long minutes = Math.abs(duration.toMinutes());
        minutes = minutes > 59 ? 0 : minutes;
        out = minutes == 1 ? minutes + " minute " : out;
        out = minutes > 1 ? minutes + " minutes " : out;
        return out;
    }

    private String getHours(Duration duration) {
        String out = "";
        long hours = Math.abs(duration.toHours());
        hours = hours > 23 ? 0 : hours;
        out = hours == 1 ? hours + " hour " : out;
        out = hours > 1 ? hours + " hours " : out;
        return out;
    }
}
