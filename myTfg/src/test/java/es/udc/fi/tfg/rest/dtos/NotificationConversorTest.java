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
        Message message = new Message(user, user, LocalDateTime.now(), "");
        Notification notification = new Notification(1L, false, user, comentario, post, message, Notification.Type.NEW_POST);

        NotificationDto notificationDto = NotificationConversor.toNotificationDto(notification);

        assertEquals(notification.isLeido(), notificationDto.isRead());
    }


    private Post getValidPost(){
        return new Post(getValidUser(), "null", "titulo", 2023, LocalDateTime.now(), new Subject("name", new University("name")), new BigDecimal(0));
    }
    private Users getValidUser(){
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }


}
