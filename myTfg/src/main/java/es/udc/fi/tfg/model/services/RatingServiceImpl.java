package es.udc.fi.tfg.model.services;

import java.math.BigDecimal;
import java.util.List;

import es.udc.fi.tfg.model.entities.*;
import es.udc.fi.tfg.model.services.exceptions.InvalidRatingException;
import es.udc.fi.tfg.model.services.exceptions.NoRatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

import java.util.Optional;

/**
 * The Class RatingServiceImpl.
 */ 
@Service
@Transactional
public class RatingServiceImpl implements RatingService{
	
	/** The rating dao. */
	@Autowired
	private RatingDao ratingDao;
	
	/** The post dao. */
	@Autowired
	private PostDao postDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/** The permission checker. */
	@Autowired
	private PermissionChecker permissionChecker;

	/**
	 * Gets the avg post ratings.
	 *
	 * @param postId the post id
	 * @return the avg post ratings
	 */
	@Override
	public float getAvgPostRatings(Long postId) {
		List<Rating> ratings= ratingDao.findByPostId(postId);
		int acc=0;
		
		if (ratings.isEmpty()) {return 0;}
		
		
		for(Rating rating : ratings) {
			acc += rating.getRating();
		}
		return (float) acc/ratings.size();
		
	}

	/**
	 * Find post ratingby user.
	 *
	 * @param postId the post id
	 * @param userId the user id
	 * @return the rating
	 * @throws InstanceNotFoundException the instance not found exception
	 * @throws NoRatingException the no rating exception
	 */
	@Override
	public Rating findPostRatingbyUser(Long postId, Long userId) throws InstanceNotFoundException, NoRatingException {
		Optional<Rating> newRating = ratingDao.findByUserIdAndPostId(userId, postId);
		
		if(!newRating.isPresent()) {
			Optional<Post> post = postDao.findById(postId);
			if(!post.isPresent()) {
				throw new InstanceNotFoundException("Post no encontrado", post);
			}
			Optional<Users> user = userDao.findById(userId);
			if(!user.isPresent()) {
				throw new InstanceNotFoundException("User no encontrado", user);
			}
			throw new NoRatingException();
		}
		return newRating.get();
	}


	/**
	 * Post rating.
	 *
	 * @param rating the rating
	 * @param userId the user id
	 * @param postId the post id
	 * @return the rating
	 * @throws InstanceNotFoundException the instance not found exception
	 * @throws InvalidRatingException the invalid rating exception
	 */
	@Override
	public Rating postRating(int rating, Long userId, Long postId) throws InstanceNotFoundException, InvalidRatingException {
		
		Users registeredUser = permissionChecker.checkUser(userId);
		
		// en caso de que el rating tenga una valoracion no valida ya no lo procesamos
		if(rating < 0 || rating > 5 ){
			throw new InvalidRatingException("Tu valoración no es válida");
		}
		
		Optional<Post> post = postDao.findById(postId);
		
		if(!post.isPresent()) {
			throw new InstanceNotFoundException("Post no encontrado", post);
		}
		Post newPost = post.get();
		
		// Checkeamos si el usuario ya ha puesto una valoracion a ese post
		Optional<Rating> checkRating = ratingDao.findByUserIdAndPostId(userId, postId);
		
		if(checkRating.isPresent()) {  // Si ya hizo una, borramos la existente
			deleteRating(checkRating.get().getId(), userId);
		}
		
		
		Rating newRating = new Rating(rating, registeredUser, newPost);
		ratingDao.save(newRating);
		float avgRating = getAvgPostRatings(postId);
		post = postDao.findById(postId);

		if(!post.isPresent()) {
			throw new InstanceNotFoundException("Post no encontrado", post);
		}
		newPost = post.get();
		//newPost.setAvgRating(new BigDecimal(avgRating));
		postDao.save(newPost);

		return newRating;
		
	}

	/**
	 * Delete rating.
	 *
	 * @param ratingId the rating id
	 * @param userId the user id
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	@Override
	public void deleteRating(Long ratingId, Long userId) throws InstanceNotFoundException {

		permissionChecker.checkUser(userId);
		
		Optional<Rating> rating = ratingDao.findById(ratingId);
		if(!rating.isPresent()) {
			throw new InstanceNotFoundException("Rating no encontrado", rating);
		}
		
		rating.get().getPost().getRatings().removeIf(ratingg -> ratingg.getId().equals(ratingId));
		
		ratingDao.deleteById(ratingId);
		
	}
	
	
	
}