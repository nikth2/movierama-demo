package assignment.movies.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "ratings")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
