package es.udc.fi.tfg.rest.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * The Class UploadRatingDto.
 */
public class UploadRatingDto{
	
	/** The rating. */
	private int rating;

	/**
	 * Instantiates a new upload rating dto.
	 */
	public UploadRatingDto() {
	}

	/**
	 * Instantiates a new upload rating dto.
	 *
	 * @param rating the rating
	 */
	public UploadRatingDto(int rating) {
		this.rating = rating;
	}
	
	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	@NotNull
	@Min(value = 0, message = "Puntúa del 1 al 5")
	@Max(value = 5, message = "Puntúa del 1 al 5")
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

}