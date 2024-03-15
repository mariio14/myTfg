package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.services.Block;
import es.udc.fi.tfg.model.entities.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CommentsAndAnswersConversorTest {

    @Test
    public void testToCommentAndAnswersDtos() {
        // Create a sample Block<CommentAndAnswers>
        List<CommentAndAnswers> commentAndAnswersList = new ArrayList<>();
        CommentAndAnswers commentAndAnswers1 = new CommentAndAnswers(
                new Comentario("Texto de prueba", null, getValidPost(), getValidUser()),
                Arrays.asList(new Comentario("Respuesta 1", null, getValidPost(), getValidUser()),
                        new Comentario("Respuesta 2", null, getValidPost(), getValidUser()))
        );
        CommentAndAnswers commentAndAnswers2 = new CommentAndAnswers(
                new Comentario("Texto de prueba 2", null, getValidPost(), getValidUser()),
                Arrays.asList(new Comentario("Respuesta 3", null, getValidPost(), getValidUser()))
        );
        commentAndAnswersList.add(commentAndAnswers1);
        commentAndAnswersList.add(commentAndAnswers2);

        Block<CommentAndAnswers> block = new Block<>(commentAndAnswersList, true);

        // Call the method to test
        BlockDto<CommentAndAnswersDto> blockDto = CommentAndAnswersConversor.toCommentAndAnswersDtos(block);

        // Assertions
        assertEquals(commentAndAnswersList.size(), blockDto.getItems().size());
        assertEquals(block.getExistMoreItems(), blockDto.getExistMoreItems());

        // You may need to add more specific assertions based on your requirements.
    }

    @Test
    public void testToCommentAndAnswersDto() {
        // Create a sample CommentAndAnswers
        CommentAndAnswers commentAndAnswers = new CommentAndAnswers(
                new Comentario("Texto de prueba", null, getValidPost(), getValidUser()),
                Arrays.asList(new Comentario("Respuesta 1", null, getValidPost(), getValidUser()),
                        new Comentario("Respuesta 2", null, getValidPost(), getValidUser()))
        );

        // Call the method to test
        CommentAndAnswersDto commentAndAnswersDto = CommentAndAnswersConversor.toCommentAndAnswersDto(commentAndAnswers);

        // Assertions
        // You may need to add more specific assertions based on your requirements.
    }

    private Post getValidPost(){
        return new Post(getValidUser(), "null", "titulo", 2022, LocalDateTime.now(), new Subject("name", new University("name")),new BigDecimal(0));
    }
    private Users getValidUser(){
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }



}
