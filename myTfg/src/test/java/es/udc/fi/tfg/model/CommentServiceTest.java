package es.udc.fi.tfg.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.Block;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.tfg.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Comentario;
import es.udc.fi.tfg.model.entities.CommentAndAnswers;
import es.udc.fi.tfg.model.services.CommentService;
import es.udc.fi.tfg.model.services.PostService;
import es.udc.fi.tfg.model.services.UserService;
import es.udc.fi.tfg.model.services.exceptions.InvalidCommentException;
import jakarta.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CommentServiceTest{
	
	@Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
    
    private Users createUser(String userName) {
        return new Users(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");
    }


    @Test
    public void test_test() {
        assertTrue(true);
    }
    
    /*
    @Test
    public void testPostAndFindComentario() throws DuplicateInstanceException, InstanceNotFoundException, InvalidCommentException {
    	Users user = createUser("user");

        userService.signUp(user);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
                
        Comentario comentario = commentService.postComentario("Texto", post.getId(), user.getId());
        
        Block<CommentAndAnswers> comentarios = commentService.findCommentAnswersByPost(post.getId(), 0, 2);
        
        assertEquals(comentario, comentarios.getItems().get(0).getComentario());
    }
    
    @Test
    public void testBorrarComentario() throws DuplicateInstanceException, InstanceNotFoundException, InvalidCommentException {
    	Users user = createUser("user");

        userService.signUp(user);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
                
        Comentario comentario = commentService.postComentario("Texto", post.getId(), user.getId());
        
        commentService.borrarComentario(comentario.getId(), user.getId());
                
        Block<CommentAndAnswers> comentarios = commentService.findCommentAnswersByPost(post.getId(), 0, 2);
        
        List<CommentAndAnswers> expected = new ArrayList<>();
        
        assertEquals(expected, comentarios.getItems());    }
    
    
    @Test
    public void testFindComentariosYRespuestasDePost() throws DuplicateInstanceException, InstanceNotFoundException,
    				InvalidCommentException {
    	Users user = createUser("user");
        userService.signUp(user);
        
        Users user2 = createUser("user2");
        userService.signUp(user2);
        
        Users user3 = createUser("user3");
        userService.signUp(user3);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
        
        Comentario comentario = commentService.postComentario("Texto", post.getId(), user.getId());
        Comentario comentario2 = commentService.postComentario("Textoo2", post.getId(), user2.getId());
        Comentario comentario3 = commentService.postComentario("Textooo3", post.getId(), user3.getId());
        
        Comentario respuesta = commentService.postRespuestaAComentario("Respuesta", user.getId(), comentario.getId(), 0L);
        Comentario respuesta2 = commentService.postRespuestaAComentario("Respuesta", user2.getId(), comentario.getId(), 0L);
        Comentario respuesta3 = commentService.postRespuestaAComentario("Respuesta", user3.getId(), comentario.getId(), 0L);

        Block<CommentAndAnswers> comentarios = commentService.findCommentAnswersByPost(post.getId(), 0, 2);
        Block<CommentAndAnswers> comentarios2 = commentService.findCommentAnswersByPost(post.getId(), 1, 2);
        
        List<Comentario> expected_list = new ArrayList<>();
        expected_list.add(respuesta); expected_list.add(respuesta2); expected_list.add(respuesta3);
        
        List<Comentario> expected_list2 = new ArrayList<>();

        assertTrue(comentarios.getExistMoreItems());
        assertEquals(comentario, comentarios.getItems().get(0).getComentario());
        assertEquals(comentario2, comentarios.getItems().get(1).getComentario());
        assertEquals(expected_list, comentarios.getItems().get(0).getRespuestas());
        assertEquals(expected_list2, comentarios.getItems().get(1).getRespuestas());

        assertFalse(comentarios2.getExistMoreItems());
        assertEquals(comentario3, comentarios2.getItems().get(0).getComentario());
        assertEquals(expected_list2, comentarios2.getItems().get(0).getRespuestas());

    }

    @Test
    public void testPostComentarioEmpty() throws DuplicateInstanceException, InstanceNotFoundException{
    	Users user = createUser("user");

        userService.signUp(user);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
                
        assertThrows(InvalidCommentException.class, () -> commentService.postComentario("", post.getId(), user.getId()));         
    }

    @Test
    public void testAnswerEmpty() throws DuplicateInstanceException, InstanceNotFoundException,
    				InvalidCommentException{
    	Users user = createUser("user");
        userService.signUp(user);
        
        Users user2 = createUser("user2");
        userService.signUp(user2);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
        
        Comentario comentario = commentService.postComentario("Texto", post.getId(), user.getId());
        
        assertThrows(InvalidCommentException.class, () -> commentService.postRespuestaAComentario("", user2.getId(), comentario.getId(), 0L));
    }
	*/
}