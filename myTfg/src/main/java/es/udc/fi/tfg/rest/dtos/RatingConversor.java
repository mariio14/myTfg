package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Rating;

/**
 * The Class RatingConversor.
 */
public class RatingConversor{

	/**
	 * To rating dto.
	 *
	 * @param rating the rating
	 * @return the rating dto
	 */
	public static final RatingDto toRatingDto(Rating rating) {
		return new RatingDto(rating.getId(), rating.getRating(), 
				rating.getUser().getId(), rating.getPost().getId());
	}
}