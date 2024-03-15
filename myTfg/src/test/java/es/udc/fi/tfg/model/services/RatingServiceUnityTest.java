package es.udc.fi.tfg.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.exceptions.InvalidRatingException;
import es.udc.fi.tfg.model.services.exceptions.NoRatingException;
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
public class RatingServiceUnityTest {

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingDao ratingDaoMock;

    @Mock
    private PostDao postDaoMock;

    @Mock
    private PermissionChecker permissionCheckerMock;

    @BeforeEach
    public void setup() throws InstanceNotFoundException {
        MockitoAnnotations.openMocks(this);
        when(permissionCheckerMock.checkUser(anyLong())).thenReturn(getValidUser());
    }

    @Test
    public void should_post_a_rating() throws InstanceNotFoundException, InvalidRatingException {
        // Configurar los comportamientos esperados
        when(postDaoMock.findById(anyLong())).thenReturn(Optional.of(getValidPost()));
        when(ratingDaoMock.findByUserIdAndPostId(anyLong(), anyLong())).thenReturn(Optional.empty());
        ArgumentCaptor<Rating> argumentCaptor = ArgumentCaptor.forClass(Rating.class);
        when(ratingDaoMock.save(argumentCaptor.capture())).thenAnswer(invocation -> {
            // Asignamos un usuario al Rating para el propósito del test
            Rating capturedRating = argumentCaptor.getValue();
            capturedRating.setUser(getValidUser());
            return capturedRating;
        });

        // Llamamos al servicio
        ratingService.postRating(4, 1L, 1L);

        // Verificamos que rating se guardo correctamente
        Rating capturedRating = argumentCaptor.getValue();
        assertEquals(4, capturedRating.getRating());
        assertNotNull(capturedRating.getUser());
        assertNotNull(capturedRating.getPost());
    }


    @Test
    public void should_delete_a_rating() throws InstanceNotFoundException {
        // Configurar los comportamientos esperados
        Rating baseRating = getBaseRating();
        when(ratingDaoMock.findById(anyLong())).thenReturn(Optional.of(baseRating));
        when(permissionCheckerMock.checkUser(eq(baseRating.getUser().getId()))).thenReturn(baseRating.getUser());

        // Llamamos al servicio
        ratingService.deleteRating(1L, baseRating.getUser().getId());

        // Verificamos que delete fue llamado con el argumento correcto
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(ratingDaoMock, times(1)).deleteById(argumentCaptor.capture());
        Long capturedId = argumentCaptor.getValue();

        assertEquals(1L, capturedId.longValue());
    }


    @Test(expected = InvalidRatingException.class)
    public void should_throw_exception_for_invalid_rating() throws InstanceNotFoundException, InvalidRatingException {
        // Llamamos al servicio (se lanza excepción)
        ratingService.postRating(6, 1L, 1L);
    }

    @Test
    public void should_get_avg_post_ratings() {
        // Configurar los comportamientos esperados
        List<Rating> ratings = new ArrayList<>();
        ratings.add(getBaseRating());
        ratings.add(new Rating(3, getValidUser(), getValidPost()));
        when(ratingDaoMock.findByPostId(anyLong())).thenReturn(ratings);

        // Llamamos al servicio
        float result = ratingService.getAvgPostRatings(1L);

        assertEquals(3.5, result, 0.01);
    }

    @Test
    public void should_find_post_rating_by_user() throws InstanceNotFoundException, NoRatingException {
        // Configurar los comportamientos esperados
        when(ratingDaoMock.findByUserIdAndPostId(anyLong(), anyLong())).thenReturn(Optional.of(getBaseRating()));

        // Llamamos al servicio
        Rating result = ratingService.findPostRatingbyUser(1L, 1L);

        assertNotNull(result);
        assertEquals(4, result.getRating());
        assertNotNull(result.getUser());
        assertNotNull(result.getPost());
    }

    @Test(expected = InstanceNotFoundException.class)
    public void should_throw_exception_for_no_rating() throws InstanceNotFoundException, NoRatingException {
        // Configurar los comportamientos esperados
        when(ratingDaoMock.findByUserIdAndPostId(anyLong(), anyLong())).thenReturn(Optional.empty());

        // Llamamos al servicio (se lanza excepcion)
        ratingService.findPostRatingbyUser(1L, 1L);
    }

    private Post getValidPost() {
        return new Post(getValidUser(), "null", "titulo", 2023, LocalDateTime.now(), new Subject("name", new University("name")), new BigDecimal(0));
    }

    private Users getValidUser() {
        Users user = new Users("nombre", "pass", "a", "b", "d@gmail.com");
        user.setId(1L);
        return user;
    }

    private Rating getBaseRating() {
        Rating rating = new Rating(4, getValidUser(), getValidPost());
        rating.setId(1L);
        return rating;

    }
}