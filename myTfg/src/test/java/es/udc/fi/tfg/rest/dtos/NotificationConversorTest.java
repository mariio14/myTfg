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
public class NotificationConversorTest {

    @Test
    public void testToNotificationDto() {
        Users user = getValidUser();
        Post post = getValidPost();
        Comentario comentario = new Comentario("comentario", post, user);
        Notification notification = new Notification(1L, false, false, user, comentario, post);

        NotificationDto notificationDto = NotificationConversor.toNotificationDto(notification);

        assertEquals(notification.isRead(), notificationDto.isRead());
        assertEquals(notification.getUser().getId(), notificationDto.getUserId());
        assertEquals(notification.getUser().getUserName(), notificationDto.getUserName());
        assertEquals(notification.getUser().getAvatar(), notificationDto.getAvatar());
        assertEquals(notification.getComentario().getPost().getId(), notificationDto.getPostId());
        assertEquals(notification.getComentario().getPost().getTitle(), notificationDto.getPostTitulo());
        assertEquals(notification.getComentario().getId(), notificationDto.getComentarioId());
        assertEquals(notification.getComentario().getTextoComentario(), notificationDto.getTextoComentario());
    }


    private Post getValidPost(){
        return new Post(getValidUser(), "null", "titulo", "description", LocalDateTime.now(), new Subject("name", new University("name")));
    }
    private Users getValidUser(){
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }


}
