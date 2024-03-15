package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RatingConversorTest {

    private Post getValidPost(){
        return new Post(getValidUser(), "titulo", "description", 2022, LocalDateTime.now(), new Subject("name", new University("name")),new BigDecimal(0));
    }
    private Users getValidUser(){
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }

    @Test
    public void testToRatingDto() {
        Rating rating = new Rating(1,getValidUser(), getValidPost());

        RatingDto ratingDto = RatingConversor.toRatingDto(rating);

        assertEquals(rating.getId(), ratingDto.getId());
        assertEquals(rating.getRating(), ratingDto.getRating());
        assertEquals(rating.getUser().getId(), ratingDto.getUserId());
        assertEquals(rating.getPost().getId(), ratingDto.getPostId());

    }
}
