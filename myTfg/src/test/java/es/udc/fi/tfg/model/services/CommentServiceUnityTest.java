package es.udc.fi.tfg.model.services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.exceptions.InvalidCommentException;
import es.udc.fi.tfg.model.entities.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CommentServiceUnityTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private ComentarioDao commentDaoMock;

    @Mock
    private PostDao postDaoMock;

    @Mock
    private NotificationDao notificationDaoMock;

    @Mock
    private PermissionChecker permissionCheckerMock;


    @BeforeEach
    public void setup() throws InstanceNotFoundException {
        MockitoAnnotations.openMocks(this);
        when(permissionCheckerMock.checkUser(anyLong())).thenReturn(getValidUser());
    }

    @Test
    public void should_post_a_comment() throws InstanceNotFoundException, InvalidCommentException {
        // Configure expected behaviours
        //when "action" takes place, we return whatever we indicate
        when(postDaoMock.findById(anyLong())).thenReturn(Optional.of(getValidPost()));
        when(commentDaoMock.findByComentarioPadreIdAndPostIdAndUserId(any(), anyLong(), anyLong()))
                .thenReturn(Optional.empty());
        when(commentDaoMock.save(any())).thenReturn(getBaseComment());

        //we call the service
        commentService.postComentario(getBaseComment().getTextoComentario(), 1L, 1L);

        //we confirm that we saved the comment properly
        ArgumentCaptor<Comentario> argumentCaptor = ArgumentCaptor.forClass(Comentario.class);
        verify(commentDaoMock, times(1)).save(argumentCaptor.capture());
        Comentario capturedComment = argumentCaptor.getValue();

        assertEquals("Comentario de prueba", capturedComment.getTextoComentario());
    }

    @Test
    public void should_post_an_answer() throws InstanceNotFoundException, InvalidCommentException {
        // Configure expected behaviours
        when(commentDaoMock.findById(anyLong())).thenReturn(Optional.of(getBaseComment()));
        when(commentDaoMock.findByComentarioPadreIdAndPostIdAndUserId(anyLong(), anyLong(), anyLong()))
                .thenReturn(Optional.empty());
        when(commentDaoMock.save(any())).thenReturn(getOtherComment());

        commentService.postRespuestaAComentario(getOtherComment().getTextoComentario(), 1L, 1L, 0L);

        ArgumentCaptor<Comentario> argumentCaptor = ArgumentCaptor.forClass(Comentario.class);
        verify(commentDaoMock, times(1)).save(argumentCaptor.capture());
        Comentario capturedComment = argumentCaptor.getValue();

        assertEquals("Test comment 2", capturedComment.getTextoComentario());
    }

    @Test
    public void testFindCommentAnswersByPost() throws InstanceNotFoundException {
        // Configurar comportamientos esperados para findCommentAnswersByPost
        Optional<Post> post = Optional.of(getValidPost());
        when(postDaoMock.findById(anyLong())).thenReturn(post);

        List<Comentario> commentsList = new ArrayList<>();
        Comentario comment = getBaseComment();
        commentsList.add(comment);
        when(commentDaoMock.findByPostIdAndComentarioPadreIdIsNull(anyLong(), any())).thenReturn(
                new SliceImpl<>(commentsList, PageRequest.of(0, 2), true));

        when(commentDaoMock.findByComentarioPadreId(anyLong())).thenReturn(new ArrayList<>());

        Block<CommentAndAnswers> result = commentService.findCommentAnswersByPost(1L, 0, 2);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(postDaoMock).findById(argumentCaptor.capture());
        assertEquals(1L, argumentCaptor.getValue().longValue());

        verify(commentDaoMock).findByPostIdAndComentarioPadreIdIsNull(1L, PageRequest.of(0, 2));

        verify(commentDaoMock).findByComentarioPadreId(argumentCaptor.capture());
        Long capturedValue = argumentCaptor.getValue();
        assertNull(capturedValue);

        assertFalse(result.getItems().isEmpty());
        assertTrue(result.getExistMoreItems());
    }

    @Test
    public void testBorrarComentario() throws InstanceNotFoundException {
    	
    	//we call the service
        commentService.borrarComentario(1L, 1L);
        
        //We verify we call the method delete with the correct argument
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
		verify(commentDaoMock).deleteById(argumentCaptor.capture());
		Long capturedId = argumentCaptor.getValue();

		assertEquals(1L, capturedId.longValue());
    }
    

    private Post getValidPost(){
        return new Post(getValidUser(), "null", "titulo", "description", LocalDateTime.now(), new Subject("name", new University("name")));
    }
    private Users getValidUser(){
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }
    private Comentario getBaseComment(){
        return new Comentario("Comentario de prueba", getValidPost(), getValidUser());
    }
    private Comentario getOtherComment(){
        return new Comentario("Test comment 2", getValidPost(), getValidUser());
    }

}
    





