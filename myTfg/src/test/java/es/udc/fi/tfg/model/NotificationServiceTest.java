package es.udc.fi.tfg.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import es.udc.fi.tfg.model.entities.Comentario;
import es.udc.fi.tfg.model.entities.Notification;
import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.CommentService;
import es.udc.fi.tfg.model.services.PostService;
import es.udc.fi.tfg.model.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.tfg.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.NotificationService;
import es.udc.fi.tfg.model.services.exceptions.InvalidCommentException;
import jakarta.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class NotificationServiceTest{
	
	@Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired 
    private NotificationService notificationService;
    
    
    private Users createUser(String userName) {
        return new Users(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");
    }

    @Test
    public void test_test() {
        assertTrue(true);
    }
    /*
    @Test
    public void testCommentAndGetNotificationAndDelete() throws InstanceNotFoundException, InvalidCommentException, DuplicateInstanceException {
    	
    	Users user = createUser("user");
    	Users user2 = createUser("user2");

        userService.signUp(user);
        userService.signUp(user2);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
        
        Comentario comentario = commentService.postComentario("Texto", post.getId(), user2.getId());
        
        assertEquals(1, notificationService.getNotReadNotifications(user.getId()));
        
        List<Notification> notifications = notificationService.getNotificationsByUser(user.getId());
        assertEquals(1, notifications.size());
        assertFalse(notifications.get(0).isRead());
        assertEquals(user, notifications.get(0).getUser());
        assertEquals(comentario, notifications.get(0).getComentario());
        
        assertEquals(0, notificationService.getNotReadNotifications(user.getId()));
        
        notificationService.deleteNotification(notifications.get(0).getId(), user.getId());
        
        notifications = notificationService.getNotificationsByUser(user.getId());
        assertEquals(0, notifications.size());
    }
    
    @Test
    public void testAnswerAndGetNotification() throws InstanceNotFoundException, InvalidCommentException, DuplicateInstanceException {
    	
    	Users user = createUser("user");
    	Users user2 = createUser("user2");

        userService.signUp(user);
        userService.signUp(user2);
        
        BigDecimal precio = new BigDecimal("15.60");

        Post post = postService.uploadPost(user.getId(), "titulo1", "descripcionnnnn",
                "hhtp://ubgvnfb.com", 1L);
        
        Comentario comentario = commentService.postComentario("Texto", post.getId(), user.getId());

        Comentario answer = commentService.postRespuestaAComentario("TextoA", user2.getId(), comentario.getId(), 0L);
        
        assertEquals(1, notificationService.getNotReadNotifications(user.getId()));
        
        List<Notification> notifications = notificationService.getNotificationsByUser(user.getId());
        assertEquals(1, notifications.size());
        assertFalse(notifications.get(0).isRead());
        assertEquals(user, notifications.get(0).getUser());
        assertEquals(answer, notifications.get(0).getComentario());
        
        assertEquals(0, notificationService.getNotReadNotifications(user.getId()));
    }
    */
}




