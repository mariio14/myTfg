package es.udc.fi.tfg.model.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class NotificationServiceUnityTest {

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Mock
    private UserDao userDaoMock;

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
    public void should_delete_notification() throws InstanceNotFoundException {
        // Configurar los comportamientos esperados
        when(notificationDaoMock.findById(anyLong())).thenReturn(Optional.of(getValidNotification()));

        // Llamamos al servicio
        notificationService.deleteNotification(1L, 1L);


        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(notificationDaoMock, times(1)).deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();

        assertEquals(1L, capturedId.longValue());
    }

    @Test
    public void should_get_notifications_by_user() throws InstanceNotFoundException {
        // Configurar los comportamientos esperados
        when(userDaoMock.findById(anyLong())).thenReturn(Optional.of(getValidUser()));
        when(notificationDaoMock.findByUserIdOrderByIdDesc(anyLong())).thenReturn(getValidNotifications());

        // Llamamos al servicio
        List<Notification> result = notificationService.getNotificationsByUser(1L);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userDaoMock, times(1)).findById(argumentCaptor.capture());
        Long capturedUserId = argumentCaptor.getValue();

        assertEquals(1L, capturedUserId.longValue());
        assertFalse(result.isEmpty());
    }

    @Test
    public void should_get_not_read_notifications() throws InstanceNotFoundException {
        // Configurar los comportamientos esperados
        when(userDaoMock.findById(anyLong())).thenReturn(Optional.of(getValidUser()));
        when(notificationDaoMock.countByUserIdAndReadFalse(anyLong())).thenReturn(2);

        // Llamamos al servicio
        int result = notificationService.getNotReadNotifications(1L);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userDaoMock, times(1)).findById(argumentCaptor.capture());
        Long capturedUserId = argumentCaptor.getValue();

        assertEquals(1L, capturedUserId.longValue());
        assertEquals(2, result);
    }

    private Users getValidUser() {
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }

    private Notification getValidNotification() {
        return new Notification(false, false, getValidUser(), getValidComment());
    }

    private List<Notification> getValidNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(getValidNotification());
        return notifications;
    }

    private Comentario getValidComment() {
        return new Comentario("Comentario de prueba", getValidPost(), getValidUser());
    }

    private Post getValidPost() {
        return new Post(getValidUser(), "null", "titulo", "description", LocalDateTime.now(), new Subject("name", new University("name")));
    }
}
