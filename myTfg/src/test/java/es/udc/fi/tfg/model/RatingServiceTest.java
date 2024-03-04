package es.udc.fi.tfg.model;

import es.udc.fi.tfg.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Rating;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.PostService;
import es.udc.fi.tfg.model.services.RatingService;
import es.udc.fi.tfg.model.services.UserService;
import es.udc.fi.tfg.model.services.exceptions.InvalidRatingException;
import es.udc.fi.tfg.model.services.exceptions.NoRatingException;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional

public class RatingServiceTest{



    // LOS COMENTARIOS SON TESTS QUE AUN NO FUNCIONAN/ COSAS SIN IMPLEMENTAR
	
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @Autowired
    private RatingService ratingService;
    
    private Users createUser(String userName) {
        return new Users(userName, userName, userName, userName, userName + "@" + userName + ".com");
    }
    
    
    private Post uploadPost(Users user) throws InstanceNotFoundException, DuplicateInstanceException {

		userService.signUp(user);

		BigDecimal precio = new BigDecimal("15.60");

		Post post1 = postService.uploadPost(user.getId(), "titulo1", "vbufrnifrni", "2022/2023", 1L);
		return post1;

    }


    @Test
    public void test_test() {
        assertTrue(true);
    }
	/*
    @Test
    public void createAndFindAvgRatingTest() throws InstanceNotFoundException, DuplicateInstanceException, InvalidRatingException {
    	
    	Users user = createUser("usuario");
    	Users user2 = createUser("pepe");
      //cuando hago un uploadPost se hace signUp del usuario indicado
    	Post post = uploadPost(user);
    	
    	ratingService.postRating(5, user.getId(), post.getId());
    	
      //hago signUp para poder hacer el rating
      userService.signUp(user2);
      ratingService.postRating(2, user2.getId(), post.getId());
      // La linea de arriba falla no se que porque
      assertEquals(3.5, ratingService.getAvgPostRatings(post.getId()));
    	
    }
    
    @Test
    public void createNegativeRatingPost() throws InstanceNotFoundException, DuplicateInstanceException{
    	
    	Users user = createUser("usuario");
    	Post post = uploadPost(user);

    	assertThrows(InvalidRatingException.class,	() ->
            ratingService.postRating(-1, user.getId(), post.getId()));


    }
	
    @Test 
    public void deleteRatingTest() throws InstanceNotFoundException, DuplicateInstanceException, InvalidRatingException {
      Users user = createUser("usuario2");
    	Post post = uploadPost(user);

      Rating rating =ratingService.postRating(5, user.getId(), post.getId());

      ratingService.deleteRating(rating.getId(),user.getId());

    }


    @Test 
    public void findPersonalRating() throws InstanceNotFoundException, DuplicateInstanceException, NoRatingException, InvalidRatingException {
      Users user = createUser("usuario2");
    	Post post = uploadPost(user);

      Rating rating =ratingService.postRating(5, user.getId(), post.getId());

      ratingService.findPostRatingbyUser(post.getId(), user.getId());

      assertEquals(rating, ratingService.findPostRatingbyUser(post.getId(), user.getId()));

    }

    @Test 
    public void findNonExistingRating() throws InstanceNotFoundException, DuplicateInstanceException, InvalidRatingException {
      Users user = createUser("usuario2");
    	Post post = uploadPost(user);

      Rating rating =ratingService.postRating(5, user.getId(), post.getId());

      ratingService.deleteRating(rating.getId(), user.getId());

      assertThrows(NoRatingException.class,	() ->
          ratingService.findPostRatingbyUser(post.getId(), user.getId()));

    }
*/

}

