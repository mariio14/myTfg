package es.udc.fi.tfg.model.services;


import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Rating;
import es.udc.fi.tfg.model.services.exceptions.InvalidRatingException;
import es.udc.fi.tfg.model.services.exceptions.NoRatingException;

/**
 * The Interface RatingService.
 */
public interface RatingService {
	
	/**
	 * Gets the avg post ratings.
	 *
	 * @param postId the post id
	 * @return the avg post ratings
	 */
	float getAvgPostRatings(Long postId);
	
	/**
	 * Find post ratingby user.
	 *
	 * @param postId the post id
	 * @param userId the user id
	 * @return the rating
	 * @throws InstanceNotFoundException the instance not found exception
	 * @throws NoRatingException the no rating exception
	 */
	Rating findPostRatingbyUser(Long postId, Long userId) throws InstanceNotFoundException, NoRatingException;
	
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
	Rating postRating(int rating, Long userId, Long postId) throws InstanceNotFoundException, InvalidRatingException;
	
	/**
	 * Delete rating.
	 *
	 * @param ratingId the rating id
	 * @param userId the user id
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	void deleteRating(Long ratingId, Long userId) throws InstanceNotFoundException;
	
}